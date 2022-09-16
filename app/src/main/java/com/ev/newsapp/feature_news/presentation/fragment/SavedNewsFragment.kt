package com.ev.newsapp.feature_news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ev.newsapp.MainActivity
import com.ev.newsapp.databinding.FragmentSavedNewsBinding
import com.ev.newsapp.feature_news.presentation.NewsViewModel
import com.ev.newsapp.feature_news.presentation.adapters.NewsAdapter

class SavedNewsFragment : Fragment(){

    private var _binding: FragmentSavedNewsBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel:NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle =Bundle().apply {
                putSerializable("article",it)
            }
            val detailNewsFragment = DetailNewsFragment()
            detailNewsFragment.arguments = bundle
            (activity as MainActivity).setCurrentFragmentBack(detailNewsFragment)
        }

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)
        })
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.rvSavedNews.apply {
            adapter =newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}