package com.example.shab3ni.accounts.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.shab3ni.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val fb = findViewById<FloatingActionButton>(R.id.fab_google)

        tabLayout.addTab(tabLayout.newTab().setText("Login"))
        tabLayout.addTab(tabLayout.newTab().setText("Signup"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginAdapter(supportFragmentManager, this, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        // animate
        fb.translationY = 300F
        fb.alpha = 0F

        fb.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(400).start()

        tabLayout.translationY = 300F
        tabLayout.alpha = 0F

        tabLayout.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(100).start()

    }
}