package com.example.onstage.postList

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onstage.R
import com.example.onstage.DetailActivity
import com.example.onstage.data.*

class NormalPostAdapter( val postsList: MutableList<Post>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.normalpost_singleitem, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val title = postsList[position].title
        val description = postsList[position].description
        val likes= postsList[position].likes
        val comments= postsList[position].comments

        //holder.postPic.setImageResource(postsList[position].photo.toInt())
        holder.postName.text = title
        holder.postRole.text = description

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.apply {
                putExtra(PHOTO, postsList[position].photo)
                putExtra(TITLE, title)
                putExtra(DESCRIPTION, description)
                putExtra(LIKES, likes)
                putExtra(COMMENTS, comments)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount() = postsList.size

}