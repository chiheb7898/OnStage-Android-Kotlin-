package com.example.onstage.commentList

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onstage.R
import com.example.onstage.DetailActivity
import com.example.onstage.data.*

class commentAdapter( val commentList: MutableList<Comment>) : RecyclerView.Adapter<commentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): commentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.commentsingleitem, parent, false)

        return commentHolder(view)
    }

    override fun onBindViewHolder(holder: commentHolder, position: Int) {

        val profilename = commentList[position].profileName
        val comment = commentList[position].comment

        holder.profilePic.setImageResource(commentList[position].profilePic)
        holder.profileName.text = profilename
        holder.comment.text = comment


    }

    override fun getItemCount() = commentList.size

}