package com.example.shab3ni.homepage.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shab3ni.R
import com.example.shab3ni.homepage.menu.ui.MenuFragment


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
        }
        btnShoppingCart?.setOnClickListener{
            btnShoppingCart?.setImageResource(R.drawable.shopping_cart_icon_clicked)

            btnMenu?.setImageResource(R.drawable.menu_icon)
            btnUserProfile?.setImageResource(R.drawable.user_icon)
        }
    }

    fun openMenuFragment(){
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MenuFragment>(R.id.homepage_fragment_container)
        }
    }
}