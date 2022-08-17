package com.example.shab3ni.user.homepage.menu.data

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("id"          ) var id: Long?      = null,
    @SerializedName("name"        ) var name: String?   = null,
    @SerializedName("imageurl"    ) var imageurl: String?   = null,
    @SerializedName("price"       ) var price: Float? = null,
    @SerializedName("description" ) var description: String?   = null,
    @SerializedName("category"    ) var category: Category? = Category()

)