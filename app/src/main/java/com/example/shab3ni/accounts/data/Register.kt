package com.example.shab3ni.accounts.data

import com.google.gson.annotations.SerializedName


data class Register(

    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null

)

/*
    Registration
    Request
    POST /register
    {
        "name": "Abc Abc",
        "email": "abc@gmail.com",
        "password": "abc-pass"
    }
    Response
    200 OK
 */