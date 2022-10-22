package com.example.onstage.commentList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onstage.R
import de.hdodenhof.circleimageview.CircleImageView

class commentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val profilePic: ImageView
    val profileName: TextView
    val comment: TextView = itemView.findViewById<TextView>(R.id.comment)

    init {
        profilePic = itemView.findViewById<CircleImageView>(R.id.profile_image)
        profileName = itemView.findViewById<TextView>(R.id.name)
    }
}