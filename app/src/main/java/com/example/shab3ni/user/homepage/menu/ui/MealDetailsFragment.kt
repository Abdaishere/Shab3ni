package com.example.shab3ni.user.homepage.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.menu.data.Meal


class MealDetailsFragment : Fragment(R.layout.fragment_meal_details){

    private var mealImg: ImageView? = null
    private var mealName: TextView? = null
    private var mealPrice: TextView? = null
    private var mealQuantity: TextView? = null
    private var mealDesc: TextView? = null
    private var mealTotalPrice: TextView? = null
    private var addToCart: Button? = null
    private var incrementQuantity: ImageButton? = null
    private var decrementQuantity: ImageButton? = null

    var adapter: MealAdapter? = null
    var mealPos: Int = 0
    var meal: Meal? = null
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
        meal = adapter?.meals?.get(mealPos)

        mealName?.text = meal?.name
        mealPrice?.text = meal?.price.toString()
        mealDesc?.text = meal?.description
        mealTotalPrice?.text = meal?.price.toString()
        Glide
            .with(view)
            .load(meal?.image)
            .placeholder(R.drawable.meal_img)
            .into(mealImg!!)

        incrementQuantity?.setOnClickListener {
            mealQuantity?.text = (mealQuantity?.text.toString().toInt() + 1).toString()
            mealTotalPrice?.text = (mealQuantity?.text.toString().toInt() * mealPrice?.text.toString().toInt()).toString()
        }

        decrementQuantity?.setOnClickListener {
            if(mealQuantity?.text.toString().toInt() > 1){
                mealQuantity?.text = (mealQuantity?.text.toString().toInt() - 1).toString()
                mealTotalPrice?.text = (mealQuantity?.text.toString().toInt() * mealPrice?.text.toString().toInt()).toString()
            }
        }

        addToCart?.setOnClickListener {

        }
    }
}