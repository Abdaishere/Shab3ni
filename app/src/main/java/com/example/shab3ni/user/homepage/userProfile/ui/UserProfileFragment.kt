package com.example.shab3ni.user.homepage.userProfile.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private var tvFirstName: TextView? = null
    private var tvLastName: TextView? = null
    private var tvEmail: TextView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvFirstName = view.findViewById(R.id.tv_firstName)
        tvLastName = view.findViewById(R.id.tv_lastName)
        tvEmail = view.findViewById(R.id.tv_email)


        // get current user from login
        tvFirstName?.text = CurrentUser.getFirstName()
        tvLastName?.text = CurrentUser.getLastName()
        tvEmail?.text = CurrentUser.getEmail()
    }
}