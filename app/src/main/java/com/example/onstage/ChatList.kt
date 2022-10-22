package com.example.onstage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onstage.data.Post
import kotlinx.android.synthetic.main.fragment_news_page.*
import com.example.onstage.postList.PostAdapter
import com.example.onstage.R
import com.example.onstage.chatList.chatAdapter
import com.example.onstage.data.Chat
import com.example.onstage.postList.NormalPostAdapter
import kotlinx.android.synthetic.main.fragment_chat_list.*
import kotlinx.android.synthetic.main.home.*

class ChatList: Fragment(){

    lateinit var recylcerchat: RecyclerView
    lateinit var recylcerchatAdapter: chatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recylcerchat = recyclerChat


        var chatList : MutableList<Chat> = ArrayList()

        for (i in 1..5) {
            chatList.add(Chat(profilePic = R.drawable.ic_vr, profileName = "Anonyme OnStage", lastmessage = "You: Hello! . 05:03" ))
            chatList.add(Chat(profilePic = R.drawable.profilepic, profileName = "Chiheb Chikhaoui", lastmessage = "You: Waa . 06:03" ))
        }


        recylcerchatAdapter = chatAdapter(chatList)

        recylcerchat.adapter = recylcerchatAdapter

        recylcerchat.layoutManager = LinearLayoutManager(recylcerchat.context, LinearLayoutManager.VERTICAL,false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}