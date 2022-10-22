package com.example.onstage.utils

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.example.onstage.data.ListPost
import com.example.onstage.data.ListUser
import com.example.onstage.data.User
import com.google.gson.JsonObject


interface ApiInterface {
    @POST("sendMsg")
    fun sendMsg(@Body body: JsonObject): Call<JsonObject>

    @POST("getUser")
    fun getUser(@Body body: JsonObject) : Call<User>

    @GET("allpost")
    fun getPosts() : Call<ListPost>

    @GET("allusers")
    fun getUsers() : Call<ListUser>

    @POST("sendCode")
    fun send(@Body body: JsonObject): Call<Number>

    @POST("verifCode")
    fun verif(@Body body: JsonObject): Call<JsonObject>

    @POST("signup")
    fun signup(@Body body: JsonObject): Call<JsonObject>

    @POST("updateUser")
    fun updateUser(@Body body: JsonObject): Call<JsonObject>

    @PUT("like")
    fun like(@Body body: JsonObject): Call<JsonObject>

    @PUT("unlike")
    fun unlike(@Body body: JsonObject): Call<JsonObject>

    companion object {

        var BASE_URL = "http://172.17.7.96:8000/"
        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}