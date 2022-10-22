package com.example.onstage.data

import androidx.annotation.DrawableRes

const val PROFILEPIC = "PROFILEPICTURE"
const val PROFILENAME = "PROFILENAMENAME"
const val LASTMESSAGE = "LASTMESSAGE"

data class Chat(

    @DrawableRes
    val profilePic: Int,

    val profileName: String,

    val lastmessage: String
)