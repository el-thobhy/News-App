package com.elthobhy.newsapp.ui.category.business

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.business.ArticleBusiness
import com.elthobhy.newsapp.databinding.ItemEverythingBinding

class BusinessAdapter : RecyclerView.Adapter<BusinessAdapter.ExploreViewHolder>() {
    private var list = ArrayList<ArticleBusiness>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExploreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_everything, parent, false)
        return ExploreViewHolder(view)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onClicked(data: ArticleBusiness)
    }

    inner class ExploreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(article: ArticleBusiness) {
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

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    fun setList(article: List<ArticleBusiness>){
        list.addAll(article)
        notifyDataSetChanged()
    }
}