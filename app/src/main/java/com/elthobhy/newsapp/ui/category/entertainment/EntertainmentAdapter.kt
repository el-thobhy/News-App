package com.elthobhy.newsapp.ui.category.entertainment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.entertainment.ArticleEntertainment
import com.elthobhy.newsapp.databinding.ItemEverythingBinding

class EntertainmentAdapter : RecyclerView.Adapter<EntertainmentAdapter.EntertainmentViewHolder>() {
    private var list = ArrayList<ArticleEntertainment>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EntertainmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_everything, parent, false)
        return EntertainmentViewHolder(view)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onClicked(data: ArticleEntertainment)
    }

    inner class EntertainmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(article: ArticleEntertainment) {
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

    override fun onBindViewHolder(holder: EntertainmentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    fun setList(article: List<ArticleEntertainment>){
        list.addAll(article)
        notifyDataSetChanged()
    }
}