package com.ev.newsapp.feature_news.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ev.newsapp.databinding.ItemArticleBinding
import com.ev.newsapp.feature_news.data.model.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticleBinding):RecyclerView.ViewHolder(binding.root)

    private val differCallback= object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article =differ.currentList[position]
        holder.binding.apply {
            Glide.with(root).load(article.urlToImage).into(ivArticleImage)
            ivArticleImage.setOnClickListener {
                onItemClickListener?.let {it(article)  }
            }
        }
        holder.binding.tvTitle.text=article.title
        holder.binding.tvDescription.text=article.description
        holder.binding.tvPublishedAt.text=article.publishedAt
        holder.binding.tvSource.text=article.source.name

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Article) -> Unit)? =null

    fun setOnItemClickListener(listener:(Article) ->Unit){
        onItemClickListener=listener
    }
}