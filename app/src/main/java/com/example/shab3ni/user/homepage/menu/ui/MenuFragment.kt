package com.example.shab3ni.user.homepage.menu.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.menu.api.productsApi
import com.example.shab3ni.user.homepage.menu.category.categoryTabLayoutAdapter
import com.example.shab3ni.user.homepage.menu.data.CategoryModel
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuFragment : Fragment(R.layout.fragment_meal) {

    lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: categoryTabLayoutAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        tabLayout = view.findViewById(R.id.tab_layout2)
        viewPager = view.findViewById(R.id.view_pager)
        getAllCategories()
        animation()
    }

    private fun animation() {
        tabLayout.translationY = -300F
        tabLayout.alpha = 0F

        tabLayout.animate().translationY(0F).alpha(1F).setDuration(500L).setStartDelay(400)
            .start()
    }


    private fun getAllCategories() {
        val context = this.context
        val call: Call<ArrayList<CategoryModel>> = productsApi.categoriesAll()
        call.enqueue(object : Callback<ArrayList<CategoryModel>> {
            override fun onResponse(
                call: Call<ArrayList<CategoryModel>>,
                response: Response<ArrayList<CategoryModel>>
            ) {
                if (response.body()?.isEmpty() == true)
                    Toast.makeText(context, "The list is empty", Toast.LENGTH_SHORT)
                        .show()

                response.body()?.forEach {
                    tabLayout.addTab(tabLayout.newTab().setText(it.name))
                }

                adapter =
                    categoryTabLayoutAdapter(
                        activity?.supportFragmentManager,
                        activity,
                        tabLayout.tabCount,
                        response.body()
                    )

                tabLayout.tabGravity = TabLayout.GRAVITY_START

                viewPager.adapter = adapter

                viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

                tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab) {
                        viewPager.currentItem = tab.position
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab) {

                    }

                    override fun onTabReselected(tab: TabLayout.Tab) {}
                })
            }

            override fun onFailure(call: Call<ArrayList<CategoryModel>>, t: Throwable) {
                Toast.makeText(context, "Error: Please try again later", Toast.LENGTH_SHORT)
                    .show()
                call.cancel()
            }
        })
    }
}