package com.example.shab3ni.accounts.api

import android.content.Context
import android.widget.Toast
import com.example.shab3ni.accounts.data.Status
import com.example.shab3ni.accounts.data.UserAuth
import com.example.shab3ni.accounts.data.UserModel
import com.example.shab3ni.user.homepage.editPage.api.adminApi
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser
import com.example.shab3ni.user.homepage.userProfile.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun register(
    context: Context,
    firstname: String,
    lastname: String,
    email: String,
    password: String,
    matchingPassword: String
) {
    val call0 = accountsApi.register(
        UserModel(firstname, lastname, email, password, matchingPassword)
    )
    call0.enqueue(object : Callback<Status> {

        override fun onResponse(call: Call<Status>, response: Response<Status>) {
            Toast.makeText(
                context,
                (response.body()?.message) ?: "Server Error",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onFailure(call: Call<Status>, t: Throwable) {
            Toast.makeText(context, "Error: Please try again", Toast.LENGTH_SHORT)
                .show()
            call.cancel()
        }

    })
}

fun resendVerifyToken(context: Context, Token: String) {
    val call = accountsApi.resendVerifyToken("Bearer $Token")
    call.enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT)
                .show()
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            Toast.makeText(context, "Error: Current User Unknown", Toast.LENGTH_SHORT)
                .show()
            call.cancel()
        }

    })
}

fun login(
    context: Context,
    email: String,
    password: String
) {
    val call0 = accountsApi.login(
        UserAuth(email = email, password = password)
    )
    try {
        if (call0.execute().isSuccessful) {

        }
        call0.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val token: String? = response.body()

                CurrentUser.setToken(token)

                if (token != null) {
                    getUserDetails(CurrentUser.getToken())
                    Toast.makeText(
                        context,
                        "Welcome ${CurrentUser.getFirstName()}",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                } else
                    Toast.makeText(context, "You are not Welcome", Toast.LENGTH_SHORT)
                        .show()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(context, "Server Offline", Toast.LENGTH_SHORT)
                    .show()
                print(t.message)
            }
        })
    } catch (e: Exception) {
        println(e.message)
    }
}

fun getUserDetails(Token: String) {

    if (Token == null)
        return

    val call1 = adminApi.getUserDetails("Bearer $Token")
    call1.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            CurrentUser.setCurrentUser(response.body())
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            print(t.message)
        }
    })
}

