package com.example.onstage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import com.example.onstage.utils.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.JsonObject

import com.google.gson.JsonParser





class MobileNum: Fragment(){
    lateinit var txtPhone: TextInputEditText
    lateinit var txtLayoutPhone: TextInputLayout
    lateinit var btnLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mobile_num,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtPhone = view.findViewById(R.id.phoneNumber)
        txtLayoutPhone = view.findViewById(R.id.phoneNumberLayout)
        btnLogin = view.findViewById(R.id.getOpt)

        btnLogin.setOnClickListener{
            doLogin()
        }
    }
    private fun doLogin(){
        if (validate()){
            val apiInterface = ApiInterface.create()
            val paramObject = JSONObject()
            paramObject.put("phone",txtPhone.text.toString())
            val jsonParser = JsonParser()
            var gsonObject = jsonParser.parse(paramObject.toString()) as JsonObject
            apiInterface.send(gsonObject).enqueue(object : Callback<Number> {

                override fun onResponse(call: Call<Number>, response: Response<Number>) {
                    val responsee = response.body().toString()
                    getActivity()
                        ?.getSharedPreferences("shared", Context.MODE_PRIVATE)
                        ?.edit()
                        ?.putString("code", responsee)
                        ?.putString("phone",txtPhone.text.toString())
                        ?.apply();
                    (activity as MainActivity).verifOPT()


                }

                override fun onFailure(call: Call<Number>, t: Throwable) {
                    Log.d("STATE", t.toString())


                }

            })

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //animation logo
    }
    private fun validate(): Boolean {
        txtLayoutPhone.error = null

        if (txtPhone.text!!.isEmpty()){
            txtLayoutPhone.error = getString(R.string.mustNotBeEmpty)
            return false
        }



        return true
    }

}
