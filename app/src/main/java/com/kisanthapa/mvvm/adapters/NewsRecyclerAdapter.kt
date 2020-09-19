package com.kisanthapa.mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kisanthapa.mvvm.R
import com.kisanthapa.mvvm.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsRecyclerAdapter : RecyclerView.Adapter<NewsRecyclerAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    // Differ Util to check whether data has changed or not
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview, parent, false)
    )

    override fun getItemCount() = differ.currentList.size
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {

            tvTitle.text = article.title
            tvDescription.text = article.description
            tvSource.text = article.source.name
            tvPublishedAt.text = article.publishedAt
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)

            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }
}