package com.elthobhy.newsapp.ui.category.sport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.sports.ArticleSports
import com.elthobhy.newsapp.databinding.ItemEverythingBinding

class SportsAdapter: RecyclerView.Adapter<SportsAdapter.SportViewHolder>() {
    private var list = ArrayList<ArticleSports>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_everything, parent, false)
        return SportViewHolder(view)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onClicked(data: ArticleSports)
    }

    inner class SportViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(article: ArticleSports) {
            val binding = ItemEverythingBinding.bind(itemView)
            binding.apply {
                tvTitle.text = article.title
                tvSource.text = article.source?.name
                tvDate.text = article.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onClicked(article)
            }
        }

    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    fun setList(article: List<ArticleSports>){
        list.addAll(article)
        notifyDataSetChanged()
    }
}