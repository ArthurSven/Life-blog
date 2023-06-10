package com.devapps.lifeblog.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devapps.lifeblog.R
import com.devapps.lifeblog.data.remote.models.Article

class NewsAdapter(param: (Any) -> Unit) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

   inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val newsImage: ImageView = itemView.findViewById(R.id.newsImage)
       val newsTitle: TextView = itemView.findViewById(R.id.newsTitle)
       val newsBody: TextView = itemView.findViewById(R.id.newsBody)
       val newsAuthor: TextView = itemView.findViewById(R.id.newsAuthor)
   }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item,
                parent,
                false)
        )
    }

    private var onItemClickListener: ((Article) ->Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.newsImage)
        holder.newsTitle.text = article.title
        holder.newsBody.text = article.body
        holder.newsAuthor.text = article.author

        setOnItemClickListener {
            onItemClickListener?.let { it(article) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}