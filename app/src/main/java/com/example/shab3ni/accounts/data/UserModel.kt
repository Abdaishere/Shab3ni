package com.example.shab3ni.accounts.data

import com.google.gson.annotations.SerializedName


data class UserModel(

    @SerializedName("firstname") var firstname: String? = null,
    @SerializedName("lastname") var lastname: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("matchingpassword") var matchingpassword: String? = null
)

/*
    Registration
    Request
    POST /register
    {
      "firstname": "string",
      "lastname": "string",
      "email": "string",
      "password": "string",
      "matchingpassword": "string"
    }
    Response
    200 OK
 */