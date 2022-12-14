package com.example.shab3ni.user.homepage.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shab3ni.R
import com.example.shab3ni.accounts.ui.login.LoginActivity
import com.example.shab3ni.user.homepage.editPage.ui.EditPageFragment
import com.example.shab3ni.user.homepage.menu.ui.MenuFragment
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser
import com.example.shab3ni.user.homepage.userProfile.ui.UserProfileFragment


class HomepageFragment : Fragment(R.layout.fragment_homepage) {

    private var btnMenu: ImageButton? = null
    private var btnUserProfile: ImageButton? = null
    private var btnShoppingCart: ImageButton? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnMenu = view.findViewById(R.id.btn_menu)
        btnUserProfile = view.findViewById(R.id.btn_userProfile)
        btnShoppingCart = view.findViewById(R.id.btn_shoppingCart)

        btnMenu?.setImageResource(R.drawable.menu_icon_clicked)
        openMenuFragment()

        btnMenu?.setOnClickListener {
            btnMenuClicked()
            openMenuFragment()
        }

        btnUserProfile?.setOnClickListener {
            if (CurrentUser.isLoggedIn()) {
                btnUserProfileClicked()
                openUserProfileFragment()
            } else {

                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)

            }
        }

        btnShoppingCart?.setOnClickListener {
            if (CurrentUser.isLoggedIn()) {
                btnShoppingCartClicked()
                openEditPageFragment()

            } else loginException()
        }
    }

    override fun onResume() {
        super.onResume()
        animation()
    }

    private fun animation() {
        val duration = 750L

        view?.translationY = 300F
        view?.alpha = 0F
        view?.animate()?.translationY(0F)?.alpha(1F)?.setDuration(600L)?.setStartDelay(50)
            ?.start()

        btnMenu?.translationY = 300F
        btnMenu?.alpha = 0F

        btnMenu?.animate()?.translationY(0F)?.alpha(1F)?.setDuration(duration)?.setStartDelay(100)
            ?.start()

        btnUserProfile?.translationY = 300F
        btnUserProfile?.alpha = 0F

        btnUserProfile?.animate()?.translationY(0F)?.alpha(1F)?.setDuration(duration)
            ?.setStartDelay(150)
            ?.start()

        btnShoppingCart?.translationY = 300F
        btnShoppingCart?.alpha = 0F

        btnShoppingCart?.animate()?.translationY(0F)?.alpha(1F)?.setDuration(duration)
            ?.setStartDelay(100)
            ?.start()
    }

    private fun btnMenuClicked() {
        btnMenu?.setImageResource(R.drawable.menu_icon_clicked)

        btnShoppingCart?.setImageResource(R.drawable.shopping_cart_icon)
        btnUserProfile?.setImageResource(R.drawable.user_icon)
    }

    private fun btnUserProfileClicked() {
        btnUserProfile?.setImageResource(R.drawable.user_icon_clicked)

        btnMenu?.setImageResource(R.drawable.menu_icon)
        btnShoppingCart?.setImageResource(R.drawable.shopping_cart_icon)
    }

    private fun btnShoppingCartClicked() {
        btnShoppingCart?.setImageResource(R.drawable.shopping_cart_icon_clicked)

        btnMenu?.setImageResource(R.drawable.menu_icon)
        btnUserProfile?.setImageResource(R.drawable.user_icon)
    }

    private fun openMenuFragment() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MenuFragment>(R.id.homepage_fragment_container)
        }
    }

    private fun openUserProfileFragment() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<UserProfileFragment>(R.id.homepage_fragment_container)
        }
    }

    private fun openEditPageFragment() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<EditPageFragment>(R.id.homepage_fragment_container)
        }
    }

    private fun loginException() {
        Toast.makeText(
            this.context, "Please login to continue",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)

    }
}