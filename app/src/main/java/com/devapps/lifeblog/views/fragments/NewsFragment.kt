package com.devapps.lifeblog.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devapps.lifeblog.R
import com.devapps.lifeblog.data.remote.models.Article
import com.devapps.lifeblog.utils.Resource
import com.devapps.lifeblog.views.MainActivity
import com.devapps.lifeblog.views.adapters.NewsAdapter
import com.devapps.lifeblog.views.viewModels.NewsViewModel

class NewsFragment : Fragment() {


   lateinit var newsViewModel: NewsViewModel
   lateinit var newsAdapter: NewsAdapter
   lateinit var newsRecyclerView: RecyclerView

   val TAG = "NewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener { article ->
            val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(article)
            findNavController().navigate(action)
        }

        newsViewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                else -> {}
            }
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { article ->
            val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(article as Article)
            findNavController().navigate(action)
        }
        newsRecyclerView = view?.findViewById(R.id.newsRecyclerView)!!

        newsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}