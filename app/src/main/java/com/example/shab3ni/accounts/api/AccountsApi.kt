package com.example.shab3ni.accounts.api

import com.example.shab3ni.accounts.data.PasswordModel
import com.example.shab3ni.accounts.data.Status
import com.example.shab3ni.accounts.data.UserAuth
import com.example.shab3ni.accounts.data.UserModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AccountsApi {

    @POST("login")
    fun login(@Body userAuth: UserAuth): Call<String>

    // saves the password via token
    @POST("savePassword")
    fun savePassword(
        @Header("Authorization") Token: String,
        @Body passwordModel: PasswordModel
    ): Call<String>

    /// On Progress
    @POST("resetpassword")
    fun resetPassword(@Body passwordModel: PasswordModel): Call<String>

    // used to change password
    @POST("changePassword")
    fun changePassword(@Body passwordModel: PasswordModel): Call<String>

    // register
    @POST("register")
    fun register(@Body userModel: UserModel): Call<Status>

    // verify by mail
    @GET("verifyRegistration")
    fun verifyRegistration(@Header("Authorization") Token: String): Call<String>

    // resend email
    @GET("resendverifytoken")
    fun resendVerifyToken(@Header("Authorization") Token: String): Call<String>
}

var gson: Gson = GsonBuilder()
    .setLenient()
    .create()

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://ntg-last.herokuapp.com/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

var accountsApi: AccountsApi = retrofit.create(AccountsApi::class.java)