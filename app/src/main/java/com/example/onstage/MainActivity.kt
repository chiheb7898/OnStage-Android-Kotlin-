package com.example.onstage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onstage.data.Post
import com.example.onstage.postList.PostAdapter
import android.content.Intent
import android.view.View
import com.example.onstage.databinding.ActivityDetailBinding
import com.example.onstage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val firstfragment=firstPage()
    val test=NewsPage()

    lateinit var recylcerPost: RecyclerView
    lateinit var recylcerPostAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,firstfragment)
            commit()

        }


    }

    fun go(view: android.view.View) {
        val fragmentveriopt=MobileNum()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragmentveriopt)
        //addToBackStack(null)
        transaction.commit()
    }
    fun gotoverifopt() {
        val fragmentverifopt=VerifOPT()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment,fragmentverifopt)
        transaction.commit()
    }
    fun verifOPT() {
        val fragmentverifopt=VerifOPT()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragmentverifopt)
        transaction.commit()
    }
    fun homePage() {
        val intent = Intent(this, HomePageActivity::class.java)
        // start your next activity
        startActivity(intent)
    }

}