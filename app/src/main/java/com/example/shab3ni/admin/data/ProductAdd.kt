package com.example.shab3ni.admin.data

import com.google.gson.annotations.SerializedName


data class ProductAdd  (

    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("imageurl"    ) var imageurl    : String? = null,
    @SerializedName("price"       ) var price       : Int?    = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("category_id" ) var categoryId  : Int?    = null

)