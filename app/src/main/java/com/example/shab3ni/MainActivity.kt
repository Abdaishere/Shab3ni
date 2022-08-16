package com.example.shab3ni

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.shab3ni.user.homepage.ui.HomepageFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openHomePage()
        }
    }

    private fun openHomePage() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<HomepageFragment>(R.id.main_fragment_container)
        }
    }


}