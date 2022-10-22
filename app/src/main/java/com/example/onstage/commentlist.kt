package com.example.onstage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onstage.chatList.chatAdapter
import com.example.onstage.commentList.commentAdapter
import com.example.onstage.data.Chat
import com.example.onstage.data.Comment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.onstage.databinding.FragmentCommentlistBinding
import kotlinx.android.synthetic.main.fragment_commentlist.*


class commentlist : BottomSheetDialogFragment(), OnBottomSheetCallbacks {
    private var _binding: FragmentCommentlistBinding? = null
    // This property is only valid between `onCreateView` and `onDestroyView`
    private val binding get() = _binding!!

    private var currentState: Int = BottomSheetBehavior.STATE_EXPANDED

    lateinit var recylcercomment: RecyclerView
    lateinit var recylcercommentAdapter: commentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        (activity as DetailActivity).setOnBottomSheetCallbacks(this)
        // NOTE: fragments outlive their views!
        //       One must clean up any references to the binging class instance here

        // Inflate the layout for this fragment
        _binding = FragmentCommentlistBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textResult.setOnClickListener {
            (activity as DetailActivity).openBottomSheet()
        }

        binding.filterImage.setOnClickListener {
            if (currentState == BottomSheetBehavior.STATE_EXPANDED) {
                (activity as DetailActivity).closeBottomSheet()
            } else {
                (activity as DetailActivity).openBottomSheet()
            }
        }
        recylcercomment = recyclerComment

        var commentList : MutableList<Comment> = ArrayList()

        for (i in 1..5) {
            commentList.add(Comment(profilePic = R.drawable.ic_vr, profileName = "Anonyme OnStage", comment = "Hello!!" ))
            commentList.add(Comment(profilePic = R.drawable.profilepic, profileName = "Chiheb Chikhaoui", comment = "Nice!" ))
        }


        recylcercommentAdapter = commentAdapter(commentList)

        recylcercomment.adapter = recylcercommentAdapter

        recylcercomment.layoutManager = LinearLayoutManager(recylcercomment.context, LinearLayoutManager.VERTICAL,false)

    }

    override fun onStateChanged(bottomSheet: View, newState: Int) {
        currentState = newState
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                binding.textResult.text = getString(R.string.zero_results)
                binding.filterImage.setImageResource(R.drawable.ic_baseline_filter_list_24)
            }
            BottomSheetBehavior.STATE_COLLAPSED -> {
                binding.textResult.text = getString(R.string.see_the_results)
                binding.filterImage.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            }
            // TODO: when the bottom sheet is moving update data
        }
    }
}


