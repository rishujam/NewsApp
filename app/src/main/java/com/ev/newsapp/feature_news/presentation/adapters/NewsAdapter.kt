package com.ev.newsapp.feature_news.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ev.newsapp.databinding.ItemArticleBinding
import com.ev.newsapp.feature_news.data.model.Article

class NewsAdapter(
    val isSavedFrag:Boolean
): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

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
            tvTitle.text=article.title
            tvDescription.text=article.description
            tvPublishedAt.text=article.publishedAt
            if(isSavedFrag){
                cdRead.visibility = View.INVISIBLE
                tvSave.text = "Remove"
                cdImage.setOnClickListener {
                    onItemClickListener?.let { it(article) }
                }
                cdSave.setOnClickListener {
                    onDeleteClickListener?.let { it(article) }
                }
            }else{
                cdRead.setOnClickListener {
                    onReadItemClickListener?.let { it(article) }
                }
                cdSave.setOnClickListener {
                    onSaveItemClickListener?.let { it(article) }
                }
            }

        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onDeleteClickListener : ((Article) -> Unit)? = null
    private var onItemClickListener: ((Article) -> Unit)? =null
    private var onSaveItemClickListener: ((Article) -> Unit)? = null
    private var onReadItemClickListener: ((Article) -> Unit)? = null

    fun saveClickListener(listener:(Article) -> Unit){
        onSaveItemClickListener = listener
    }

    fun readClickListener(listener: (Article) -> Unit){
        onReadItemClickListener = listener
    }

    fun onArticleClick(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

    fun onDeleteClick(listener: (Article) -> Unit){
        onDeleteClickListener = listener
    }
}