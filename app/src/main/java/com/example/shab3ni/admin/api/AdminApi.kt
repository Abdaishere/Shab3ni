package com.example.shab3ni.admin.api

import com.example.shab3ni.accounts.api.AccountsApi
import com.example.shab3ni.admin.data.CategoryAdd
import com.example.shab3ni.admin.data.ProductAdd
import com.example.shab3ni.user.homepage.userProfile.data.User
import com.example.shab3ni.user.homepage.menu.data.Category
import com.example.shab3ni.user.homepage.menu.data.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST


interface AdminApi {

    @POST("add/product")
    fun addProduct(@Body productAdd: ProductAdd): Product

    @POST("add/category")
    fun addCategory(@Body categoryAdd: CategoryAdd): Category

    @DELETE("deleteproduct/")
    fun deleteProduct(ID: Int): Boolean

    @GET("users/all")
    fun usersAll(): ArrayList<User>

    @GET("getuserdetails")
    fun getUserDetails(): ArrayList<User>

}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://ntg-last.herokuapp.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var adminApi: AdminApi = retrofit.create(AdminApi::class.java)