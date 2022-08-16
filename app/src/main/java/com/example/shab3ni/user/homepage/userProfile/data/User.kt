package com.example.shab3ni.user.homepage.userProfile.data

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("firstname" ) var firstname : String? = null,
    @SerializedName("lastname"  ) var lastname  : String? = null,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("role"      ) var role      : String? = null
)