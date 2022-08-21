package com.example.shab3ni.accounts.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.shab3ni.MainActivity
import com.example.shab3ni.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var imageView: ImageView
    lateinit var viewPager: ViewPager
    lateinit var textView: TextView

    private lateinit var fb: FloatingActionButton
    private val duration = 600L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.welcomeText)
        fb = findViewById(R.id.fab_google)

        tabLayout.addTab(tabLayout.newTab().setText("Login"))
        tabLayout.addTab(tabLayout.newTab().setText("Signup"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginAdapter(supportFragmentManager, this, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        fb.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        animation()
    }

    private fun animation() {
        fb.translationY = 300F
        fb.alpha = 0F

        fb.animate().translationY(0F).alpha(1F).setDuration(duration).setStartDelay(300).start()

        tabLayout.translationY = 300F
        tabLayout.alpha = 0F

        tabLayout.animate().translationY(0F).alpha(1F).setDuration(duration).setStartDelay(100)
            .start()

        imageView.scaleX = 1.3F
        imageView.scaleY = 1.3F
        imageView.rotation = 250F
        imageView.alpha = 0F

        imageView.animate().rotation(1F).scaleX(1f).scaleY(1f).alpha(1F).setDuration(700)
            .setStartDelay(400).start()

        textView.translationY = 300F
        textView.alpha = 0F

        textView.animate().translationY(0F).alpha(1F).setDuration(duration).setStartDelay(150)
            .start()

    }
}