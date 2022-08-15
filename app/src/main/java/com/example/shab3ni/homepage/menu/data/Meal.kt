package com.example.shab3ni.homepage.menu.data

import com.google.gson.annotations.SerializedName

data class Meal (

    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("price"       ) var price       : Double? = null,
    @SerializedName("image"       ) var image       : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("category_id" ) var categoryId  : Int?    = null

)