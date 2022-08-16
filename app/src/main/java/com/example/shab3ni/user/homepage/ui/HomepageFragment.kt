package com.example.shab3ni.user.homepage.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.menu.ui.MenuFragment
import com.example.shab3ni.user.homepage.userProfile.ui.UserProfileFragment


class HomepageFragment : Fragment(R.layout.fragment_homepage){

    var btnMenu: ImageButton? = null
    var btnUserProfile: ImageButton? = null
    var btnShoppingCart: ImageButton? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnMenu = view.findViewById(R.id.btn_menu)
        btnUserProfile = view.findViewById(R.id.btn_userProfile)
        btnShoppingCart = view.findViewById(R.id.btn_shoppingCart)

        btnMenu?.setImageResource(R.drawable.menu_icon_clicked)
        openMenuFragment()

        btnMenu?.setOnClickListener{
            btnMenu?.setImageResource(R.drawable.menu_icon_clicked)

            btnShoppingCart?.setImageResource(R.drawable.shopping_cart_icon)
            btnUserProfile?.setImageResource(R.drawable.user_icon)
            openMenuFragment()
        }
        btnUserProfile?.setOnClickListener{
            btnUserProfile?.setImageResource(R.drawable.user_icon_clicked)

            btnMenu?.setImageResource(R.drawable.menu_icon)
            btnShoppingCart?.setImageResource(R.drawable.shopping_cart_icon)
            openUserProfileFragment()
        }
        btnShoppingCart?.setOnClickListener{
//            btnShoppingCart?.setImageResource(R.drawable.shopping_cart_icon_clicked)
//
//            btnMenu?.setImageResource(R.drawable.menu_icon)
//            btnUserProfile?.setImageResource(R.drawable.user_icon)

            Toast.makeText(this.context, "Shopping cart isn\'t available yet.", LENGTH_SHORT).show()
        }
    }

    fun openMenuFragment(){
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MenuFragment>(R.id.homepage_fragment_container)
        }
    }

    fun openUserProfileFragment(){
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<UserProfileFragment>(R.id.homepage_fragment_container)
        }
    }
}