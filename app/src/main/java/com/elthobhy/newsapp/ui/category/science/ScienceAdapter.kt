package com.elthobhy.newsapp.ui.category.science

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.ArticleScience
import com.elthobhy.newsapp.databinding.ItemEverythingBinding

class ScienceAdapter: RecyclerView.Adapter<ScienceAdapter.ScienceViewHolder>() {
    private var list = ArrayList<ArticleScience>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScienceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_everything, parent, false)
        return ScienceViewHolder(view)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onClicked(data: ArticleScience)
    }

    inner class ScienceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(article: ArticleScience) {
            val binding = ItemEverythingBinding.bind(itemView)
            binding.apply {
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageEverything)
                tvTitle.text = article.title
                tvSource.text = article.source?.name
                tvDate.text = article.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onClicked(article)
            }
        }

    }

    override fun onBindViewHolder(holder: ScienceViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    fun setList(article: List<ArticleScience>){
        list.addAll(article)
        notifyDataSetChanged()
    }
}