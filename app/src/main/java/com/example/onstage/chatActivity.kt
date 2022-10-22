package com.example.onstage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.onstage.data.DESCRIPTION
import com.example.onstage.data.PHOTO
import com.example.onstage.data.TITLE
import kotlinx.android.synthetic.main.activity_chat.*

class chatActivity : AppCompatActivity() {

    lateinit var userPic : ImageView
    lateinit var userName : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        userPic = findViewById(R.id.userPic)
        userName = findViewById(R.id.userName)

        userPic.setImageResource(intent.getIntExtra(PHOTO, 0))

        val name = intent.getStringExtra(TITLE)
        val role = intent.getStringExtra(DESCRIPTION)

        title= "$name Detail"

        userName.text = "$name"
    }

    fun back(view: android.view.View) {
        onBackPressed()
    }
    fun writemessage(view: android.view.View) {
        messageZone.setHint("Type a message...")
    }
}