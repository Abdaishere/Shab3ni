package com.example.shab3ni.user.homepage.userProfile.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.shab3ni.R
import com.example.shab3ni.accounts.api.accountsApi
import com.example.shab3ni.accounts.data.PasswordModel
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var changePassword: Button
    private lateinit var pass: EditText
    private lateinit var conf_pass: EditText
    private lateinit var cvEmail: CardView
    private lateinit var cvFirstName: CardView
    private lateinit var cvLastName: CardView

    private var changePassOpen: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvFirstName = view.findViewById(R.id.tv_firstName)
        tvLastName = view.findViewById(R.id.tv_lastName)
        tvEmail = view.findViewById(R.id.tv_email)
        changePassword = view.findViewById(R.id.btn_changpass)
        pass = view.findViewById(R.id.password)
        conf_pass = view.findViewById(R.id.password_conform)

        cvEmail = view.findViewById(R.id.cv_email)
        cvFirstName = view.findViewById(R.id.cv_firstName)
        cvLastName = view.findViewById(R.id.cv_lastName)

        changePassword.setOnClickListener {
            if (!changePassOpen) {
                changePassOpen = true
                pass.animate().translationX(0f).alpha(1f).setDuration(500)
                    .setStartDelay(800).start()

                conf_pass.animate().translationX(0f).alpha(1f).setDuration(500)
                    .setStartDelay(1000).start()

                changePassword.animate().translationY(25f).setDuration(500)
                    .setStartDelay(200)
                    .start()
            } else {

                if (pass.text.equals(conf_pass.text) && pass.text.isNotEmpty())
                    changePassword()
                else
                    Toast.makeText(context, "Error: Please try again", Toast.LENGTH_SHORT)
                        .show()
            }
        }

        tvFirstName.text = CurrentUser.getFirstName()
        tvLastName.text = CurrentUser.getLastName()
        tvEmail.text = CurrentUser.getEmail()

        animation()
    }

    private fun animation() {
        cvEmail.translationX = 800f
        cvFirstName.translationX = 800f
        cvLastName.translationX = 800f

        cvEmail.alpha = 0f
        cvFirstName.alpha = 0f
        cvLastName.alpha = 0f
        changePassword.alpha = 0f

        cvFirstName.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(200)
            .start()

        cvLastName.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(300)
            .start()

        cvEmail.animate().translationX(0f).alpha(1f).setDuration(500)
            .setStartDelay(500).start()

        changePassword.animate().translationY(-150f).alpha(1f)
            .setDuration(500)
            .setStartDelay(700)
            .start()

        pass.translationX = 800f
        conf_pass.translationX = 800f
        pass.alpha = 0f
        conf_pass.alpha = 0f

    }

    private fun changePassword() {
        val context = this.context
        val call: Call<String> = accountsApi.changePassword(
            PasswordModel(
                tvEmail.text.toString(),
                pass.text.toString(),
                conf_pass.text.toString()
            )
        )
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Toast.makeText(context, "${response.body()}", Toast.LENGTH_SHORT)
                    .show()
                // get back
                pass.animate().translationX(800f).alpha(0f).setDuration(500)
                    .setStartDelay(200).start()

                conf_pass.animate().translationX(800f).alpha(0f).setDuration(500)
                    .setStartDelay(800).start()

                changePassword.animate().translationY(25f).setDuration(500)
                    .setStartDelay(1000)
                    .start()

                changePassOpen = false
                pass.setText("")
                conf_pass.setText("")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(context, "Error: Please try again later", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
}