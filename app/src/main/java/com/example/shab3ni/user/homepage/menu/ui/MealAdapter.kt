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
import com.example.shab3ni.user.homepage.menu.data.Meal

@Suppress("UNUSED_PARAMETER")
class MealAdapter(var meals: List<Meal>?, private val onMealListener: OnMealListener) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>(), Parcelable {
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

            itemView.setOnClickListener(this)
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
        val meal = meals?.get(position)
        holder.tvMealName.text = meal?.name
        holder.tvMealPrice.text = "${meal?.price} LE"

        Glide
            .with(holder.itemView)
            .load(meal?.image)
            .into(holder.ivMealImg)
    }


    override fun getItemCount() = meals?.size ?: 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MealAdapter> {
        override fun createFromParcel(parcel: Parcel): MealAdapter {
            return MealAdapter(parcel)
        }

        override fun newArray(size: Int): Array<MealAdapter?> {
            return arrayOfNulls(size)
        }
    }

}