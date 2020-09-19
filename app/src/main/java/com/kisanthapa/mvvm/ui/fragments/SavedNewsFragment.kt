package com.kisanthapa.mvvm.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kisanthapa.mvvm.R
import com.kisanthapa.mvvm.adapters.NewsRecyclerAdapter
import com.kisanthapa.mvvm.ui.NewsActivity
import com.kisanthapa.mvvm.ui.NewsViewModel

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private lateinit var newsAdapter: NewsRecyclerAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel



//        newsAdapter.setOnItemClickListener {
//            val bundle = Bundle().apply {
//                putSerializable("article", it)
//            }
//            findNavController().navigate(
//                R.id.action_savedNewsFragment_to_articleFragment,
//                bundle
//            )
//        }


    }
}