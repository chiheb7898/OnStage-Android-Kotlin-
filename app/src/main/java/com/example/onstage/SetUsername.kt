package com.example.onstage

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import android.provider.MediaStore
import android.util.Log
import java.lang.Exception
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager
import android.widget.Button

import androidx.core.content.ContextCompat
import com.example.onstage.utils.ApiInterface
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SetUsername: Fragment() {
    val config: HashMap<String, Any> = HashMap()
    private val IMAGE_REQ = 1
    private var IMAGE_PATH : String ?= ""
    private var profilePic: ImageView? = null
    private var selectedImageUri: Uri? = null
    lateinit var txtName: TextInputEditText
    lateinit var txtLayoutName: TextInputLayout
    lateinit var btnNext: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_username, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        config.put("cloud_name", "jiheng")
        config.put("api_key", "351692624125855")
        config.put("api_secret", "pJNUl_rsk6HB1oeuGBO-sW6VYgk")
        MediaManager.init(requireActivity().application, config)
        profilePic = view.findViewById(R.id.profile_image)
        profilePic!!.setOnClickListener {
            requestPermission();
        }
        txtLayoutName = view.findViewById(R.id.nameLayout)
        txtName = view.findViewById(R.id.nameEditText)
        btnNext = view.findViewById(R.id.btnNext)

        btnNext.setOnClickListener{
            Next()
        }

    }
    private fun Next(){
        if (validate()) {
            var sh = getActivity()?.getSharedPreferences("shared", Context.MODE_PRIVATE)
            val result = sh?.getString("result", null)
            val phone = sh?.getString("phone", null)
            val userId = sh?.getString("userId", null)

            //signup
            val paramObject = JSONObject()
            paramObject.put("name", txtName.text.toString())
            paramObject.put("phone", phone)
            paramObject.put("picture", IMAGE_PATH)

            val paramObject1 = JSONObject()
            paramObject1.put("_id", userId)
            paramObject1.put("name", txtName.text.toString())
            paramObject1.put("picture", IMAGE_PATH)
            val jsonParser = JsonParser()
            var gsonObject1 = jsonParser.parse(paramObject1.toString()) as JsonObject
            var gsonObject = jsonParser.parse(paramObject.toString()) as JsonObject
            val apiInterface = ApiInterface.create()
            if (result == "not found") {
                apiInterface.signup(gsonObject).enqueue(object : Callback<JsonObject> {

                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        val userID = response.body()?.get("userId").toString()
                        sh?.edit()
                            ?.putString("userId",userID)
                            ?.apply();
                        (activity as MainActivity).homePage()
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                        Toast.makeText(activity,  "Connexion error!", Toast.LENGTH_LONG).show();


                    }

                })
            } else {
                apiInterface.updateUser(gsonObject1).enqueue(object : Callback<JsonObject> {

                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        (activity as MainActivity).homePage()
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                        Toast.makeText(activity,  "Connexion error!", Toast.LENGTH_LONG).show();


                    }

                })
            }
        }
    }
    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            selectImage()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), IMAGE_REQ
            )
        }
    }
    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*" // if you want to you can use pdf/gif/video
        intent.action = Intent.ACTION_GET_CONTENT
        startForResultOpenGallery.launch(intent)
    }
    private val startForResultOpenGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedImageUri = result.data!!.data
                profilePic!!.setImageURI(selectedImageUri)
                uploadToCloudinary(selectedImageUri)
            }
        }
    private fun uploadToCloudinary(filepath: Uri?) {
        MediaManager.get().upload(filepath).unsigned("sqnsfsup").callback(object :
            UploadCallback {
            override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
                IMAGE_PATH=resultData?.get("secure_url").toString()

            }

            override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {

            }

            override fun onReschedule(requestId: String?, error: ErrorInfo?) {

            }

            override fun onError(requestId: String?, error: ErrorInfo?) {
                Toast.makeText(activity,  "Connexion error!", Toast.LENGTH_SHORT).show()
            }

            override fun onStart(requestId: String?) {

            }
        }).dispatch()
    }

    private fun validate(): Boolean {
        txtLayoutName.error = null

        if (txtName.text!!.isEmpty()){
            txtLayoutName.error = getString(R.string.mustNotBeEmpty)
            return false
        }



        return true
    }

}


