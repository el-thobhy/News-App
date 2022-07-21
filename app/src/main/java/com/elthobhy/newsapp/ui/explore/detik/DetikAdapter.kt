package com.elthobhy.newsapp.ui.explore.detik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.ArticleDetik
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.databinding.ItemTopHeadlinesBinding

class DetikAdapter:RecyclerView.Adapter<DetikAdapter.DetikViewHolder>() {
    private val list = ArrayList<ArticleDetik>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetikAdapter.DetikViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_headlines, parent, false)
        return DetikViewHolder(view)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ArticleDetik)
    }

    inner class DetikViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTopHeadlinesBinding.bind(itemView)
        fun bind(detikEntity: ArticleDetik) {
            binding.apply {
                Glide.with(itemView)
                    .load(detikEntity.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageTopHeadline)
                tvTitleTopHeadlines.text = detikEntity.title
                tvSourceName.text = detikEntity.source?.name
                tvDateNews.text = detikEntity.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(detikEntity)
            }
        }
    }

    override fun onBindViewHolder(holder: DetikAdapter.DetikViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(detikEntity: List<ArticleDetik>) {
        list.addAll(detikEntity)
        notifyDataSetChanged()
    }
}