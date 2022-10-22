package com.example.onstage

import android.os.Bundle
import android.util.Log
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
import com.example.onstage.data.ListPost
import com.example.onstage.postList.NormalPostAdapter
import com.example.onstage.utils.ApiInterface
import kotlinx.android.synthetic.main.home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPage: Fragment(){

    lateinit var recylcerPost: RecyclerView
    lateinit var nrecyclerPost:RecyclerView
    lateinit var recylcerPostAdapter: PostAdapter
    lateinit var nrecylcerPostAdapter: NormalPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_page,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recylcerPost = recyclerPost

        nrecyclerPost= normalrecyclerPost


        var postList : MutableList<Post> = ArrayList()
        var normalpostList : MutableList<Post> = ArrayList()

        val apiInterface1 = ApiInterface.create().getPosts()
        apiInterface1.enqueue(object : Callback<ListPost> {

            override fun onResponse(call: Call<ListPost>, response: Response<ListPost>) {
                Log.d("STATE", "message1")
                val postist : ListPost = response.body() as ListPost

                for(i: Post in postist.posts){

                    postList.add(
                        Post(likes=i.likes,_id=i._id,title=i.title,description=i.description,
                            photo=i.photo,
                            postedBy="i.postedBy",
                            comments=i.comments)
                    )

                }
                for (i: Post in postist.posts) {
                    normalpostList.add(Post(likes=i.likes,_id=i._id,title=i.title,description=i.description,
                        photo=i.photo,
                        postedBy="i.postedBy",
                        comments=i.comments))

                }
                recylcerPostAdapter = PostAdapter(postList)

                recylcerPost.adapter = recylcerPostAdapter
                // nrecylcerPostAdapter = NormalPostAdapter(normalpostList)

                //nrecyclerPost.adapter = nrecylcerPostAdapter

            }
            override fun onFailure(call: Call<ListPost>, t: Throwable) {
                Log.d("ERROR", t.toString())
            }

        })

        recylcerPost.layoutManager = LinearLayoutManager(recylcerPost.context, LinearLayoutManager.HORIZONTAL,false)

        nrecyclerPost.layoutManager = LinearLayoutManager(recylcerPost.context, LinearLayoutManager.VERTICAL,false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}