package com.example.onstage

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingsPageActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
    }
    fun backtoprevious(view: View) {
        onBackPressed();
    }

    fun goaccountSettings(view: android.view.View) {
        val intent = Intent(this, EditProfileActivity::class.java)
        // start your next activity
        startActivity(intent)
    }

}