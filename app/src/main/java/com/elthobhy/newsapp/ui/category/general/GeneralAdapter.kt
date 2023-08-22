package com.elthobhy.newsapp.ui.category.general

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.general.ArticleGeneral
import com.elthobhy.newsapp.databinding.ItemEverythingBinding

class GeneralAdapter: RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder>() {
    private var list = ArrayList<ArticleGeneral>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneralViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_everything, parent, false)
        return GeneralViewHolder(view)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onClicked(data: ArticleGeneral)
    }

    inner class GeneralViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(article: ArticleGeneral) {
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

    override fun onBindViewHolder(holder: GeneralViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    fun setList(article: List<ArticleGeneral>){
        list.addAll(article)
        notifyDataSetChanged()
    }
}