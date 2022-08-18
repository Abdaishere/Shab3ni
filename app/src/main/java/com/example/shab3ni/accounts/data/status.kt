package com.example.shab3ni.accounts.data

import com.google.gson.annotations.SerializedName


data class Status(

    @SerializedName("status") var status: String? = null,
    @SerializedName("message") var message: String? = null

)