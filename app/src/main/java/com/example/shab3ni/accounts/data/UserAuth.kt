package com.example.shab3ni.accounts.data

import com.google.gson.annotations.SerializedName


data class UserAuth (

    @SerializedName("email"    ) var email    : String? = null,
    @SerializedName("password" ) var password : String? = null

)

/*
    Make a login screen. A user can login with email and password
    Login
    POST /login
    {
        "email": "abc@gmail.com",
        "password": "abc-pass"
    }
    Response
    {
        "name": "Abc Abc",
        "email": "abc@gmail.com",
        "token": "abc_token"
    }
 */