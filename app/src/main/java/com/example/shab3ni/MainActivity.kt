package com.example.shab3ni

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shab3ni.user.homepage.ui.HomepageFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openHomePage()
    }

    private fun openHomePage() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<HomepageFragment>(R.id.main_fragment_container)
        }
    }


}