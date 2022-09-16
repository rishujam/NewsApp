package com.ev.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.ev.newsapp.R
import com.ev.newsapp.databinding.ActivityMainBinding
import com.ev.newsapp.feature_news.presentation.NewsViewModel
import com.ev.newsapp.feature_news.presentation.fragment.BreakingNewsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val breakingNewsFragment = BreakingNewsFragment()
        setCurrentFragment(breakingNewsFragment)
    }

    fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    fun setCurrentFragmentBack(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            addToBackStack("b")
            commit()
        }
}