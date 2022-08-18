package com.example.shab3ni.user.homepage.menu.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.shab3ni.MainActivity
import com.example.shab3ni.R
import com.example.shab3ni.accounts.ui.login.LoginActivity
import com.example.shab3ni.user.homepage.editPage.api.adminApi
import com.example.shab3ni.user.homepage.menu.data.Product
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MealDetailsFragment : Fragment(R.layout.fragment_meal_details) {

    private var mealImg: ImageView? = null
    private var mealName: TextView? = null
    private var mealPrice: TextView? = null
    private var mealQuantity: TextView? = null
    private var mealDesc: TextView? = null
    private var mealTotalPrice: TextView? = null
    private var fadeView: View? = null
    private var btnDetailsAddToCart: com.google.android.material.floatingactionbutton.FloatingActionButton? =
        null
    private var incrementQuantity: ImageButton? = null
    private var decrementQuantity: ImageButton? = null

    private var adapter: ProductAdapter? = null
    private var mealPos: Int = 0
    private var product: Product? = null

    private var itemDeleted: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mealImg = view.findViewById(R.id.iv_detailsMealImg)
        mealName = view.findViewById(R.id.tv_detailsMealName)
        mealDesc = view.findViewById(R.id.tv_detailsMealDesc)
        mealPrice = view.findViewById(R.id.tv_detailsMealPrice)
        mealQuantity = view.findViewById(R.id.tv_detailsMealQuantity)
        mealTotalPrice = view.findViewById(R.id.tv_detailsMealTotalPrice)
        btnDetailsAddToCart = view.findViewById(R.id.btn_detailsAddToCart)
        incrementQuantity = view.findViewById(R.id.btn_detailsQuantityAdd)
        decrementQuantity = view.findViewById(R.id.btn_detailsQuantityRemove)

        view.translationY = 700F
        view.alpha = 0F

        view.animate().translationY(0F).alpha(1F).setDuration(350).setStartDelay(100)
            .start()

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


        btnDetailsAddToCart?.setOnClickListener {
            if (CurrentUser.isLoggedIn()) {
                deleteProduct(product?.id)

                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)

            } else
                loginException()

        }
    }

    private fun deleteProduct(ID: Long?) {
        print(ID)
        val call = adminApi.deleteProduct("Bearer " + CurrentUser.getToken(), Id = ID)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.body() == true) {
                    Toast.makeText(context, "Product Deleted Successfully", Toast.LENGTH_SHORT)
                        .show()
                    itemDeleted = true
                } else
                    Toast.makeText(
                        context,
                        "Product Wasn't Deleted Successfully",
                        Toast.LENGTH_SHORT
                    )
                        .show()

            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(context, "Error: Current User Unknown", Toast.LENGTH_SHORT)
                    .show()
                call.cancel()
            }
        })
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