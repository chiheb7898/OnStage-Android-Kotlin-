package com.example.onstage

import android.content.Context
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.onstage.data.*
import com.example.onstage.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.example.onstage.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var listener: OnBottomSheetCallbacks? = null
    private var mBottomSheetBehavior: BottomSheetBehavior<View?>? = null

    lateinit var postPic : ImageView
    lateinit var postTitle : TextView
    lateinit var postDescription : TextView
    lateinit var like : TextView
    lateinit var comments : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        postPic = findViewById(R.id.postPic)
        postTitle = findViewById(R.id.postName)
        postDescription = findViewById(R.id.postRole)
        like = findViewById(R.id.likes)
        comments = findViewById(R.id.cmnts)

        postPic.setImageResource(intent.getIntExtra(PHOTO, 0))

        val title = intent.getStringExtra(TITLE)
        val description = intent.getStringExtra(DESCRIPTION)
        val likes=intent.getStringArrayListExtra(LIKES)
        val comments=intent.getStringExtra(COMMENTS)



        postTitle.text = "$title"
        postDescription.text = "$description"
        if (likes != null) {
            like.text = likes.size.toString()
        }
        else like.text="0"

        // Set the elevation equal to zero to remove any shadows between the action bar
        // (same thing for the toolbar) and the layout
        //supportActionBar?.elevation = 0f


    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

    }

    fun setOnBottomSheetCallbacks(onBottomSheetCallbacks: OnBottomSheetCallbacks) {
        this.listener = onBottomSheetCallbacks
    }

    fun openBottomSheet() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun closeBottomSheet() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }


    private fun toggleButton(button: MaterialButton) {
        if (button.textColors.defaultColor == ContextCompat.getColor(this, R.color.white)) {
            button.strokeColor =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.OnGreen))
            button.setTextColor(ContextCompat.getColor(this, R.color.OnGreen))
        } else {
            button.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            button.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    private fun setToggleMenuButtons() {
        /*binding.materialButtonToggleGroupSort.addOnButtonCheckedListener { _, checkedId, _ ->
            toggleButton(findViewById(checkedId))
        }
        binding.materialButtonToggleGroupDifficulty.addOnButtonCheckedListener { _, checkedId, _ ->
            toggleButton(findViewById(checkedId))
        }*/
    }

    private fun configureBackdrop() {
        val fragment = supportFragmentManager.findFragmentById(R.id.filter_fragment)

        (fragment?.view?.parent as View).let { view ->
            BottomSheetBehavior.from(view).let { bs ->

                bs.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        // Call the interface to notify a state change
                        listener?.onStateChanged(bottomSheet, newState)
                    }
                })

                // Set the bottom sheet expanded by default
                bs.state = BottomSheetBehavior.STATE_EXPANDED

                mBottomSheetBehavior = bs
            }
        }
    }

    fun showcomments(view: android.view.View) {
        configureBackdrop()
    }
}