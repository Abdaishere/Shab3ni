@file:Suppress("DEPRECATION")

package com.example.shab3ni.user.homepage.menu.category

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.menu.data.CategoryModel
import com.example.shab3ni.user.homepage.menu.ui.MealDetailsFragment
import com.example.shab3ni.user.homepage.menu.ui.ProductAdapter


class CategoryRecyclerViewFragment(private val categoryModel: CategoryModel) :
    Fragment(R.layout.fragment_meal_view),
    ProductAdapter.OnMealListener {

    private var mealImg: ImageView? = null
    private var mealName: TextView? = null
    private var mealPrice: TextView? = null
    private var adapter: ProductAdapter? = null
    private var rvMeals: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mealImg = view.findViewById(R.id.iv_mealImg)
        mealName = view.findViewById(R.id.tv_mealName)
        mealPrice = view.findViewById(R.id.tv_mealPrice)
        rvMeals = view.findViewById(R.id.rv_menu)

        val layoutManager = GridLayoutManager(this.context, 2)

        adapter = ProductAdapter(null, this)

        rvMeals?.adapter = adapter
        rvMeals?.layoutManager = layoutManager

        getProducts()
        animation()
    }

    private fun getProducts() {
        adapter?.products = categoryModel.products
    }

    override fun onMealClicked(position: Int) {

        val bundle = Bundle()
        bundle.putParcelable("adapter", adapter)
        bundle.putInt("meal position", position)

        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add<MealDetailsFragment>(R.id.main_fragment_container, args = bundle)
            addToBackStack(null)
        }
    }

    private fun animation() {
        rvMeals?.alpha = 0F
        rvMeals?.animate()?.alpha(1F)?.setDuration(400L)
            ?.setStartDelay(50)
            ?.start()
    }
}

