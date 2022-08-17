package com.example.shab3ni.accounts.api

import com.example.shab3ni.accounts.data.PasswordModel
import com.example.shab3ni.accounts.data.UserAuth
import com.example.shab3ni.accounts.data.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface AccountsApi {

    @POST("login")
    fun login(@Body userAuth: UserAuth)

    // saves the password via token
    @POST("savePassword")
    fun savePassword(@Query("token") token: String, @Body passwordModel: PasswordModel)

    /// On Progress
    @POST("resetpassword")
    fun resetPassword(@Body passwordModel: PasswordModel)

    //
    @POST("changePassword")
    fun changePassword(@Body passwordModel: PasswordModel)

    // register
    @POST("register")
    fun register(@Body userModel: UserModel)

    // verify by mail
    @GET("verifyRegistration")
    fun verifyRegistration(@Query("token") token: String): String

    // resend email
    @GET("resendverifytoken")
    fun resendVerifyToken(@Query("token") token: String): String
}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://ntg-last.herokuapp.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var accountsApi: AccountsApi = retrofit.create(AccountsApi::class.java)