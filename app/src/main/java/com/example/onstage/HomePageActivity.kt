package com.example.onstage

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class HomePageActivity: AppCompatActivity() {


    val fragmentnews = NewsPage()
    val fragmentChat= ChatList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.newsframe, fragmentnews)
            commit()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navbar)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    //item.setTitle(getResources().getString(R.string.app_name));

                    switchtoNews()

                }
                R.id.chat -> {
                    switchtoChat()
                }
                R.id.addpost -> {
                    val intent = Intent(this, AddPostActivity::class.java)
                    // start your next activity
                    startActivity(intent)

                }
            }
            false
        }



    }

    fun gosettings(view: android.view.View) {
        val intent = Intent(this, SettingsPageActivity::class.java)
        // start your next activity
        startActivity(intent)
    }
    fun switchtoChat(){
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.newsframe, fragmentChat).commit()
    }
    fun switchtoNews(){
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.newsframe, fragmentnews).commit()
    }

}