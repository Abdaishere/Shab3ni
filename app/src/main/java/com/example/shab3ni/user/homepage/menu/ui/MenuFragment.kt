package com.example.shab3ni.user.homepage.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.menu.category.categoryTabLayoutAdapter
import com.google.android.material.tabs.TabLayout


class MenuFragment : Fragment(R.layout.fragment_menu) {

    lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        tabLayout = view.findViewById(R.id.tab_layout2)
        viewPager = view.findViewById(R.id.view_pager)

        tabLayout.addTab(tabLayout.newTab().setText("Main Meal"))
        tabLayout.addTab(tabLayout.newTab().setText("Sides"))
        tabLayout.addTab(tabLayout.newTab().setText("Dessert"))
        tabLayout.addTab(tabLayout.newTab().setText("Snacks"))
        tabLayout.tabGravity = TabLayout.GRAVITY_START

        val adapter =
            categoryTabLayoutAdapter(
                activity?.supportFragmentManager,
                activity,
                tabLayout.tabCount
            )
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

//        animation()
    }


}