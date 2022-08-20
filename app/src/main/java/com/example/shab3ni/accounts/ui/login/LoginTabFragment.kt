package com.example.shab3ni.accounts.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shab3ni.MainActivity
import com.example.shab3ni.R
import com.example.shab3ni.accounts.api.accountsApi
import com.example.shab3ni.accounts.data.UserAuth
import com.example.shab3ni.user.homepage.editPage.api.adminApi
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser
import com.example.shab3ni.user.homepage.userProfile.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginTabFragment : Fragment() {
    private var duration = 400
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var forgetPass: TextView
    private lateinit var login: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.login_tab_fragment, container, false) as ViewGroup
        email = root.findViewById(R.id.email)
        pass = root.findViewById(R.id.password)
        forgetPass = root.findViewById(R.id.forget_password)
        login = root.findViewById(R.id.btn_login)
        login.setOnClickListener {
            var errors = 0
            var error: String? = null
            if (email.text.toString().isEmpty()) {
                errors++
                error = "Email is required!"
            }
            if (pass.text.toString().isEmpty()) {
                errors++
                error = "Password is required!"
            }
            when (errors) {
                0 -> {
                    login(
                        context,
                        email.text.toString(),
                        pass.text.toString()
                    )

                }
                1 -> {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(context, "Error: Please try again", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        animation()
        return root
    }

    fun login(
        context: Context?,
        email: String,
        password: String
    ) {
        val call0 = accountsApi.login(
            UserAuth(email = email, password = password)
        )
        try {
            call0.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val token: String? = response.body()

                    CurrentUser.setToken(token)

                    if (token != null) {
                        getUserDetails(context, token, great = true)

                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)

                    } else
                        Toast.makeText(context, "Failed to login", Toast.LENGTH_SHORT)
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

    private fun animation() {
        email.translationX = 800f
        pass.translationX = 800f
        forgetPass.translationX = 800f
        login.translationX = 800f
        email.alpha = 0f
        pass.alpha = 0f
        forgetPass.alpha = 0f
        login.alpha = 0f
        email.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(300).start()
        pass.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(500).start()
        forgetPass.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(500).start()
        login.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(700).start()
    }
}

fun getUserDetails(context: Context?, Token: String, great: Boolean) {
    val call1 = adminApi.getUserDetails("Bearer $Token")
    call1.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            CurrentUser.setCurrentUser(response.body())

            if (great) {
                Toast.makeText(context, "Welcome ${CurrentUser.getFirstName()}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            print(t.message)
        }
    })
}