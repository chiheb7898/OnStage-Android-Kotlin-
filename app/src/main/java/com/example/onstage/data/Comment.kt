package com.example.onstage.data

import androidx.annotation.DrawableRes

const val CPROFILEPIC = "PROFILEPICTURE"
const val CPROFILENAME = "PROFILENAMENAME"
const val COMMENT = "COMMENT"

data class Comment(

    val profilePic: Int,

    val profileName: String,

    val comment: String
)