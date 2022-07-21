package com.elthobhy.newsapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.databinding.ItemTopHeadlinesBinding

class HeadlineAdapter: RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder>(){
    private val list = ArrayList<ArticleHeadline>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadlineAdapter.HeadlineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_headlines, parent, false)
        return HeadlineViewHolder(view)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: ArticleHeadline)
    }

    inner class HeadlineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemTopHeadlinesBinding.bind(itemView)
        fun bind(headlineEntity: ArticleHeadline) {
            binding.apply {
                Glide.with(itemView)
                    .load(headlineEntity.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageTopHeadline)
                tvTitleTopHeadlines.text = headlineEntity.title
                tvSourceName.text = headlineEntity.source?.name
                tvDateNews.text = headlineEntity.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(headlineEntity)
            }
        }
    }

    override fun onBindViewHolder(holder: HeadlineAdapter.HeadlineViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    fun setList(headlineEntity: List<ArticleHeadline>){
        list.addAll(headlineEntity)
        notifyDataSetChanged()
    }
}