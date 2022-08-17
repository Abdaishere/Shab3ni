package com.example.shab3ni.user.homepage.menu.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.shab3ni.R
import com.example.shab3ni.accounts.ui.login.LoginActivity
import com.example.shab3ni.user.homepage.menu.data.Product
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser


class MealDetailsFragment : Fragment(R.layout.fragment_meal_details) {

    private var mealImg: ImageView? = null
    private var mealName: TextView? = null
    private var mealPrice: TextView? = null
    private var mealQuantity: TextView? = null
    private var mealDesc: TextView? = null
    private var mealTotalPrice: TextView? = null
    private var addToCart: com.google.android.material.floatingactionbutton.FloatingActionButton? =
        null
    private var incrementQuantity: ImageButton? = null
    private var decrementQuantity: ImageButton? = null

    private var adapter: ProductAdapter? = null
    private var mealPos: Int = 0
    private var product: Product? = null
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mealImg = view.findViewById(R.id.iv_detailsMealImg)
        mealName = view.findViewById(R.id.tv_detailsMealName)
        mealDesc = view.findViewById(R.id.tv_detailsMealDesc)
        mealPrice = view.findViewById(R.id.tv_detailsMealPrice)
        mealQuantity = view.findViewById(R.id.tv_detailsMealQuantity)
        mealTotalPrice = view.findViewById(R.id.tv_detailsMealTotalPrice)
        addToCart = view.findViewById(R.id.btn_detailsAddToCart)
        incrementQuantity = view.findViewById(R.id.btn_detailsQuantityAdd)
        decrementQuantity = view.findViewById(R.id.btn_detailsQuantityRemove)

        mealPos = requireArguments().getInt("meal position")
        adapter = requireArguments().getParcelable("adapter")
        product = adapter?.products?.get(mealPos)

        mealName?.text = product?.name
        mealPrice?.text = product?.price.toString()
        mealDesc?.text = "Category: ${product?.category?.name} \n\n${product?.description}"
        mealTotalPrice?.text = product?.price.toString()
        Glide
            .with(view)
            .load(product?.imageurl)
            .placeholder(R.drawable.meal_img)
            .into(mealImg!!)

        incrementQuantity?.setOnClickListener {
            if (CurrentUser.isLoggedIn()) {
                mealQuantity?.text = (mealQuantity?.text.toString().toInt() + 1).toString()
                mealTotalPrice?.text =
                    ((mealQuantity?.text.toString().toInt() * mealPrice?.text.toString()
                        .toFloat()).toString())
            } else loginException()
        }

        decrementQuantity?.setOnClickListener {

            if (CurrentUser.isLoggedIn()) {
                if (mealQuantity?.text.toString().toInt() > 1) {
                    mealQuantity?.text = (mealQuantity?.text.toString().toInt() - 1).toString()
                    mealTotalPrice?.text =
                        ((mealQuantity?.text.toString().toInt() * mealPrice?.text.toString()
                            .toFloat()).toString())
                }
            } else loginException()
        }

        addToCart?.setOnClickListener {
            if (CurrentUser.isLoggedIn()) {

            } else
                loginException()

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