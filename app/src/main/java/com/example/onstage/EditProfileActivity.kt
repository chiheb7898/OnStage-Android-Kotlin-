package com.example.onstage

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EditProfileActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profilesettings)
    }

    fun discardchanges(view: android.view.View) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Discard Changes")
            .setMessage("Are you sure you want to discard the changes you made?")
            .setNegativeButton("No Thanks") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Discard") { dialog, which ->
                finish()
            }
            .show()
    }
}