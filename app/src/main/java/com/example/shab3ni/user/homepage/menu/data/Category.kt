package com.example.shab3ni.user.homepage.menu.data

import com.google.gson.annotations.SerializedName

data class Category (

    @SerializedName("id"   ) var id   : Long?    = null,
    @SerializedName("name" ) var name : String? = null

)