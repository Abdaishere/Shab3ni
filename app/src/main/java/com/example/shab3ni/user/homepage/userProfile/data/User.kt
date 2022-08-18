package com.example.shab3ni.user.homepage.userProfile.data

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("id"        ) var id        : Long?     = null,
    @SerializedName("firstname" ) var firstname : String?  = null,
    @SerializedName("lastname"  ) var lastname  : String?  = null,
    @SerializedName("email"     ) var email     : String?  = null,
    @SerializedName("password"  ) var password  : String?  = null,
    @SerializedName("role"      ) var role      : String?  = null,
    @SerializedName("enabled"   ) var enabled   : Boolean? = null

)