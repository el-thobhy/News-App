package com.elthobhy.newsapp.ui.favorite.adapter.suara

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.suara.ArticleSuara
import com.elthobhy.newsapp.databinding.ItemTopHeadlinesBinding

class FavoriteSuaraAdapter: RecyclerView.Adapter<FavoriteSuaraAdapter.FavoriteViewHolder>() {
    private val list = ArrayList<ArticleSuara>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_headlines, parent, false)
        return FavoriteViewHolder(view)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ArticleSuara)
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTopHeadlinesBinding.bind(itemView)
        fun bind(entity: ArticleSuara) {
            binding.apply {
                Glide.with(itemView)
                    .load(entity.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageTopHeadline)
                tvTitleTopHeadlines.text = entity.title
                tvSourceName.text = entity.source?.name
                tvDateNews.text = entity.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(entity)
            }
        }
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(entity: List<ArticleSuara>) {
        list.clear()
        list.addAll(entity)
        notifyDataSetChanged()
    }
}