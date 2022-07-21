package com.elthobhy.newsapp.ui.explore.suara

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.ArticleSuara
import com.elthobhy.newsapp.databinding.ItemTopHeadlinesBinding

class SuaraAdapter: RecyclerView.Adapter<SuaraAdapter.SuaraViewHolder>(){
    private val list = ArrayList<ArticleSuara>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuaraAdapter.SuaraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_headlines, parent, false)
        return SuaraViewHolder(view)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: ArticleSuara)
    }

    inner class SuaraViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemTopHeadlinesBinding.bind(itemView)
        fun bind(suaraEntity: ArticleSuara) {
            binding.apply {
                Glide.with(itemView)
                    .load(suaraEntity.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageTopHeadline)
                tvTitleTopHeadlines.text = suaraEntity.title
                tvSourceName.text = suaraEntity.source?.name
                tvDateNews.text = suaraEntity.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(suaraEntity)
            }
        }
    }

    override fun onBindViewHolder(holder: SuaraAdapter.SuaraViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    fun setList(suaraEntity: List<ArticleSuara>){
        list.addAll(suaraEntity)
        Log.e("debug", "setList: $list", )
        notifyDataSetChanged()
    }
}