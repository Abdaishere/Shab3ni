package com.example.shab3ni.user.homepage.menu.api

import com.example.shab3ni.user.homepage.menu.data.CategoryModel
import com.example.shab3ni.user.homepage.menu.data.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ProductsApi {

    @GET("products/all")
    fun productsAll(): Call<ArrayList<Product>>

    @GET("getproductbyid/")
    fun getProductById(ID: Long?): Call<Product>

    @GET("getcategorybyid/")
    fun getCategoryById(ID: Long?): Call<CategoryModel>

    @GET("categories/all")
    fun categoriesAll(): Call<ArrayList<CategoryModel>>
}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://ntg-last.herokuapp.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var productsApi: ProductsApi = retrofit.create(ProductsApi::class.java)