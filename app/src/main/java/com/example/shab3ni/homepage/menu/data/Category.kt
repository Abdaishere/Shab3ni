package com.example.shab3ni.homepage.menu.data

import com.google.gson.annotations.SerializedName

data class Category (

    @SerializedName("id"       ) var id       : Int?                = null,
    @SerializedName("name"     ) var name     : String?             = null,
    @SerializedName("products" ) var products : ArrayList<Meal> = arrayListOf()

)