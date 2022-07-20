package com.elthobhy.newsapp.ui.explore.kapanlagi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.ItemTopHeadlinesBinding
import com.elthobhy.newsapp.ui.home.HeadlineAdapter

class KapanlagiAdapter: RecyclerView.Adapter<KapanlagiAdapter.KapanlagiViewHolder>(){
    private val list = ArrayList<Article>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KapanlagiAdapter.KapanlagiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_headlines, parent, false)
        return KapanlagiViewHolder(view)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Article)
    }

    inner class KapanlagiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemTopHeadlinesBinding.bind(itemView)
        fun bind(kapanlagiEntity: Article) {
            binding.apply {
                Glide.with(itemView)
                    .load(kapanlagiEntity.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageTopHeadline)
                tvTitleTopHeadlines.text = kapanlagiEntity.title
                tvSourceName.text = kapanlagiEntity.source?.name
                tvDateNews.text = kapanlagiEntity.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(kapanlagiEntity)
            }
        }
    }

    override fun onBindViewHolder(holder: KapanlagiAdapter.KapanlagiViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    fun setList(kapanlagiEntity: List<Article>){
        list.addAll(kapanlagiEntity)
        Log.e("debug", "setList: $list", )
        notifyDataSetChanged()
    }
}