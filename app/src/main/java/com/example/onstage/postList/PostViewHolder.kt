package com.example.onstage.postList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onstage.R


class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val postPic : ImageView
    val postName : TextView
    val postRole : TextView = itemView.findViewById<TextView>(R.id.postRole)

    init {
        postPic = itemView.findViewById<ImageView>(R.id.postPic)
        postName = itemView.findViewById<TextView>(R.id.postName)
    }

}