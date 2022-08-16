package com.example.shab3ni

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.shab3ni.admin.ui.AdminFragment
import com.example.shab3ni.user.homepage.ui.HomepageFragment
import android.widget.Button
import com.example.shab3ni.accounts.ui.login.LoginActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            openHomePage()
        }

    }
    fun openHomePage(){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<HomepageFragment>(R.id.main_fragment_container)
            

        // test login
        val btn = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btn)
        btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}