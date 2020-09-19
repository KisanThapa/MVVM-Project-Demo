package com.kisanthapa.mvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisanthapa.mvvm.R
import com.kisanthapa.mvvm.adapters.NewsRecyclerAdapter
import com.kisanthapa.mvvm.ui.NewsActivity
import com.kisanthapa.mvvm.ui.NewsViewModel
import com.kisanthapa.mvvm.utility.Resource
import com.kisanthapa.mvvm.utility.SEARCH_NEWS_TIME_DELAY
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private lateinit var viewModel: NewsViewModel

    private lateinit var newsAdapter: NewsRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

        // Init Recycler Adapter
        setUpRecyclerView()

        // Observe data in view model
        viewModel.searchNews.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Error -> {
                    Log.d("KKKK", "Error: message:${response.message}  dat:${response.data}")
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Success -> {
                    Log.d("KKKK", "Error: message:${response.message}  dat:${response.data}")
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        // hit api when Edit text changes
        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }


        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsRecyclerAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }
}