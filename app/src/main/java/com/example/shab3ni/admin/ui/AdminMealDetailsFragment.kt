package com.example.shab3ni.admin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.menu.data.Product
import com.example.shab3ni.user.homepage.menu.ui.ProductAdapter

class AdminMealDetailsFragment : Fragment(R.layout.fragment_admin_meal_details) {

    private var mealImg: ImageView? = null
    private var mealName: TextView? = null
    private var mealPrice: TextView? = null
    private var mealQuantity: TextView? = null
    private var mealDesc: TextView? = null
    private var btnDeleteMeal: Button? = null

    var adapter: ProductAdapter? = null
    var mealPos: Int = 0
    var product: Product? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mealImg = view.findViewById(R.id.iv_detailsMealImg)
        mealName = view.findViewById(R.id.tv_detailsMealName)
        mealDesc = view.findViewById(R.id.tv_detailsMealDesc)
        mealPrice = view.findViewById(R.id.tv_detailsMealPrice)
        mealQuantity = view.findViewById(R.id.tv_detailsMealQuantity)
        btnDeleteMeal = view.findViewById(R.id.btn_adminDetailsDelete)

        mealPos = requireArguments().getInt("meal position")
        adapter = requireArguments().getParcelable("adapter")
        product = adapter?.products?.get(mealPos)

        mealName?.text = product?.name
        mealPrice?.text = product?.price.toString()
        mealDesc?.text = product?.description
        Glide
            .with(view)
            .load(product?.imageurl)
            .placeholder(R.drawable.meal_img)
            .into(mealImg!!)


        btnDeleteMeal?.setOnClickListener {
            //TODO: delete the meal from database

            parentFragmentManager.popBackStack() // return to admin screen
        }
    }
}