package com.example.onstage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.onstage.utils.ApiInterface
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.preference.PreferenceManager




class VerifOPT: Fragment() {
    lateinit var txtCode: TextInputEditText
    lateinit var txtLayoutCode: TextInputLayout
    lateinit var btnVerif: Button
    lateinit var mSharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verif_o_p_t, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtLayoutCode = view.findViewById(R.id.codeLayout)
        txtCode = view.findViewById(R.id.codeEditText)
        btnVerif = view.findViewById(R.id.btnVerif)

        btnVerif.setOnClickListener{
            doVerif()
        }

    }
    private fun doVerif(){
        if (validate()) {
            val apiInterface = ApiInterface.create()
            val sh = getActivity()?.getSharedPreferences("shared", Context.MODE_PRIVATE)
            val code2 =sh?.getString("code", null)
            val phone =sh?.getString("phone", null)


            val paramObject = JSONObject()
            paramObject.put("code1",txtCode.text.toString())
            paramObject.put("code2",code2)
            paramObject.put("phone",phone)

            val jsonParser = JsonParser()
            var gsonObject = jsonParser.parse(paramObject.toString()) as JsonObject
            apiInterface.verif(gsonObject).enqueue(object : Callback<JsonObject> {

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val userID = response.body()?.get("userId").toString().replace("\"", "")
                    when (response.body()?.get("found").toString()) {
                        "\""+"fail"+"\"" -> {
                            Toast.makeText(activity,"wrong code!",Toast.LENGTH_LONG).show();
                        }
                        "\""+"user not found"+"\"" -> {
                            sh?.edit()
                                ?.putString("result", "not found")
                                ?.apply();
                            (activity as MainActivity).gotoverifopt()
                        }
                        "\""+"must complete info"+"\"" -> {
                            sh?.edit()
                                ?.putString("userId",userID)
                                ?.putString("result", "complete")
                                ?.apply();
                            (activity as MainActivity).gotoverifopt()
                        }
                        "\""+"found"+"\"" -> {
                            sh?.edit()
                                ?.putString("userId",userID)
                                ?.apply();
                            (activity as MainActivity).homePage()
                        }
                    }


                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(activity,  "Connexion error!", Toast.LENGTH_LONG).show();                    //Toast.makeText(this@MainActivity, "Connexion error!", Toast.LENGTH_SHORT).show()


                }

            })
        }
    }
    private fun validate(): Boolean {
        txtLayoutCode.error = null

        if (txtCode.text!!.isEmpty()){
            txtLayoutCode.error = getString(R.string.mustNotBeEmpty)
            return false
        }



        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}

