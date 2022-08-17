package com.example.shab3ni.user.homepage.menu.ui

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.menu.data.Product

class ProductAdapter(var products: List<Product>?, private val onMealListener: OnMealListener) :
    RecyclerView.Adapter<ProductAdapter.MealViewHolder>(), Parcelable {
    class MealViewHolder(view: View, onMealListener: OnMealListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val tvMealName: TextView
        val tvMealPrice: TextView
        val ivMealImg: ImageView
        private val onMealListener: OnMealListener

        init {
            tvMealName = view.findViewById(R.id.tv_mealName)
            tvMealPrice = view.findViewById(R.id.tv_mealPrice)
            ivMealImg = view.findViewById(R.id.iv_mealImg)
            this.onMealListener = onMealListener

            animation()

            itemView.setOnClickListener(this)
        }

        private fun animation() {
            tvMealName.alpha = 0F
            tvMealName.animate().alpha(1F).setDuration(200L)
                .setStartDelay(50)
                .start()

            tvMealPrice.alpha = 0F
            tvMealPrice.animate().alpha(1F).setDuration(200L)
                .setStartDelay(50)
                .start()

            ivMealImg.alpha = 0F
            ivMealImg.animate().alpha(1F).setDuration(400L)
                .setStartDelay(50)
                .start()
        }

        override fun onClick(p0: View?) {
            onMealListener.onMealClicked(adapterPosition)
        }
    }

    interface OnMealListener {
        fun onMealClicked(position: Int)
    }


    constructor(parcel: Parcel) : this(
        TODO("meals"),
        TODO("onMealListener")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_layout, parent, false)

        return MealViewHolder(view, onMealListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = products?.get(position)
        holder.tvMealName.text = meal?.name
        holder.tvMealPrice.text = "${meal?.price} LE"

        Glide
            .with(holder.itemView)
            .load(meal?.imageurl)
            .into(holder.ivMealImg)
    }


    override fun getItemCount() = products?.size ?: 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductAdapter> {
        override fun createFromParcel(parcel: Parcel): ProductAdapter {
            return ProductAdapter(parcel)
        }

        override fun newArray(size: Int): Array<ProductAdapter?> {
            return arrayOfNulls(size)
        }
    }

}