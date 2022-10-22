package com.example.onstage.data

const val PHOTO = "PHOTO"
const val TITLE = "TITLE"
const val DESCRIPTION = "DESCRIPTION"
const val LIKES="LIKES"
const val POSTEDBY="POSTEDBY"
const val ID="ID"
const val COMMENTS="COMMENTS"

data class Post(
    val likes: Array<String> ,
    val _id : String,
    val title: String,
    val description : String,
    val photo: String,
    val postedBy: String,
    val comments:Array<Comment>,
)