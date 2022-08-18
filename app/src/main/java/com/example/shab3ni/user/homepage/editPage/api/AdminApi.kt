package com.example.shab3ni.user.homepage.editPage.api

import com.example.shab3ni.user.homepage.editPage.data.CategoryAdd
import com.example.shab3ni.user.homepage.editPage.data.ProductAdd
import com.example.shab3ni.user.homepage.menu.data.Category
import com.example.shab3ni.user.homepage.menu.data.Product
import com.example.shab3ni.user.homepage.userProfile.data.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface AdminApi {

    @POST("add/product")
    fun addProduct(
        @Header("Authorization") Token: String,
        @Body productAdd: ProductAdd
    ): Call<Product>

    @POST("add/category")
    fun addCategory(
        @Header("Authorization") Token: String,
        @Body categoryAdd: CategoryAdd
    ): Call<Category>

    @DELETE("deleteproduct/{productId}")
    fun deleteProduct(
        @Header("Authorization") Token: String,
        @Path("productId") Id: Long?
    ): Call<Boolean>

    @GET("getuserdetails")
    fun getUserDetails(@Header("Authorization") Token: String): Call<User>

}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://ntg-last.herokuapp.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var adminApi: AdminApi = retrofit.create(AdminApi::class.java)