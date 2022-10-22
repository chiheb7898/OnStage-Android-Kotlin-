package com.example.onstage.chatList

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onstage.R
import com.example.onstage.DetailActivity
import com.example.onstage.chatActivity
import com.example.onstage.data.*

class chatAdapter( val chatList: MutableList<Chat>) : RecyclerView.Adapter<chatHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_single_item, parent, false)

        return chatHolder(view)
    }

    override fun onBindViewHolder(holder: chatHolder, position: Int) {

        val profilename = chatList[position].profileName
        val lastmessage = chatList[position].lastmessage

        holder.profilePic.setImageResource(chatList[position].profilePic)
        holder.profileName.text = profilename
        holder.lastmessage.text = lastmessage

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, chatActivity::class.java)
            intent.apply {
                putExtra(PHOTO, chatList[position].profilePic)
                putExtra(TITLE, profilename)
                putExtra(DESCRIPTION, lastmessage)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount() = chatList.size

}