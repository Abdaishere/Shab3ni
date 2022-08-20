package com.example.shab3ni.accounts.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shab3ni.R
import com.example.shab3ni.accounts.api.accountsApi
import com.example.shab3ni.accounts.data.Status
import com.example.shab3ni.accounts.data.UserModel
import com.example.shab3ni.accounts.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupTabFragment : Fragment() {

    private val duration = 600
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var confPass: EditText
    private lateinit var signup: Button
    private var errors = 0
    private var error: String? = null
    private val nameString: Array<String> = arrayOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.register_tab_fragment, container, false) as ViewGroup
        name = root.findViewById(R.id.name)
        email = root.findViewById(R.id.email)
        pass = root.findViewById(R.id.password)
        confPass = root.findViewById(R.id.password_conform)
        signup = root.findViewById(R.id.btn_signup)
        animation()
        signup.setOnClickListener {
            errors = 0
            error = null
            val nameString =
                name.text.toString().split(" ").toTypedArray()
            checkInput()

            when (errors) {
                0 -> {
                    register(
                        requireContext(),
                        nameString[0],
                        nameString[1],
                        email.text.toString(),
                        pass.text.toString(),
                        confPass.text.toString()
                    )
                }
                1 -> {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(context, "Error: Please Check your Inputs", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        return root
    }

    private fun checkInput() {
        if (nameString[0].isEmpty()) {
            errors++
            error = "First Name is required!"
        }
        if (nameString[1].isEmpty()) {
            errors++
            error = "Second Name is required!"
        }
        if (email.text.toString().isEmpty()) {
            errors++
            error = "Email is required!"
        }
        if (pass.text.toString().isEmpty()) {
            errors++
            error = "Password is required!"
        }
        if (confPass.text.toString().isEmpty()) {
            errors++
            error = "Conform Password is required!"
        }
        if (pass.text.toString() == confPass.text.toString()) {
            errors++
            error = "Passwords don't match"
        }
    }

    private fun register(
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

                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {
                Toast.makeText(context, "Error: Please try again", Toast.LENGTH_SHORT)
                    .show()
                call.cancel()
            }

        })
    }

    private fun animation() {
        name.translationX = 800f
        email.translationX = 800f
        pass.translationX = 800f
        confPass.translationX = 800f
        signup.translationX = 800f
        name.alpha = 0f
        email.alpha = 0f
        pass.alpha = 0f
        confPass.alpha = 0f
        signup.alpha = 0f
        name.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(500).start()
        email.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(600).start()
        pass.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(800).start()
        confPass.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(1000).start()
        signup.animate().translationX(0f).alpha(1f).setDuration(duration.toLong())
            .setStartDelay(1100).start()
    }
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