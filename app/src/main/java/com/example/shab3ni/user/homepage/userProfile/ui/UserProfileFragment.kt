package com.example.shab3ni.user.homepage.userProfile.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private var tvFirstName: TextView? = null
    private var tvLastName: TextView? = null
    private var tvEmail: TextView? = null
    private var changePassword: Button? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvFirstName = view.findViewById(R.id.tv_firstName)
        tvLastName = view.findViewById(R.id.tv_lastName)
        tvEmail = view.findViewById(R.id.tv_email)
        changePassword = view.findViewById<Button>(R.id.btn_login)

        // get current user from login
        tvFirstName?.text = CurrentUser.getFirstName()
        tvLastName?.text = CurrentUser.getLastName()
        tvEmail?.text = CurrentUser.getEmail()

        animation()
    }

    private fun animation() {

        tvFirstName?.translationX = 800f
        tvLastName?.translationX = 800f
        tvEmail?.translationX = 800f
        changePassword?.translationX = 800f

        tvFirstName?.alpha = 0f
        tvLastName?.alpha = 0f
        tvEmail?.alpha = 0f
        changePassword?.alpha = 0f

        tvFirstName?.animate()?.translationX(0f)?.alpha(1f)?.setDuration(500L)?.setStartDelay(300)
            ?.start()

        tvLastName?.animate()?.translationX(0f)?.alpha(1f)?.setDuration(500L)?.setStartDelay(500)
            ?.start()

        tvEmail?.animate()?.translationX(0f)?.alpha(1f)?.setDuration(500L)
            ?.setStartDelay(500)?.start()

        changePassword?.animate()?.translationX(0f)?.alpha(1f)?.setDuration(500L)
            ?.setStartDelay(700)
            ?.start()
    }
}