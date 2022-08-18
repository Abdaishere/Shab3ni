package com.example.shab3ni.accounts.data

import com.google.gson.annotations.SerializedName


data class PasswordModel (

    @SerializedName("email"       ) var email       : String? = null,
    @SerializedName("oldpassword" ) var oldpassword : String? = null,
    @SerializedName("newpassword" ) var newpassword : String? = null

)
/*
    {
      "email": "string",
      "oldpassword": "string",
      "newpassword": "string"
    }
 */