package com.ev.newsapp.feature_news.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ev.newsapp.databinding.FragmentBreakingNewsBinding
import com.ev.newsapp.feature_news.presentation.NewsViewModel
import com.ev.newsapp.feature_news.presentation.adapters.NewsAdapter
import com.ev.newsapp.feature_news.utils.Resource

class BreakingNewsFragment: Fragment() {

    private var _binding: FragmentBreakingNewsBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel:NewsViewModel
    lateinit var  newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreakingNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("BreakingNewsFragment","An error occured : $message")
                    }
                }
                is Resource.Loading ->
                    showProgressBar()
            }
        })
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.GONE
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter =newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}