package com.elthobhy.newsapp.ui.category.technology

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.ItemTopHeadlinesBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TechnologyAdapter: ListAdapter<Domain, TechnologyAdapter.TechnologyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Domain>(){
            override fun areItemsTheSame(oldItem: Domain, newItem: Domain): Boolean {
                return oldItem.content == newItem.content
            }

            override fun areContentsTheSame(oldItem: Domain, newItem: Domain): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var list = ArrayList<Domain>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TechnologyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_headlines, parent, false)
        return TechnologyViewHolder(view)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onClicked(data: Domain)
    }

    inner class TechnologyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(article: Domain) {
            val binding = ItemTopHeadlinesBinding.bind(itemView)
            binding.apply {
                if(article.urlToImage.isNullOrEmpty()){
                    lottie.visibility = View.VISIBLE
                    imageTopHeadline.visibility = View.GONE
                }
                else{
                    imageTopHeadline.visibility=View.VISIBLE
                    lottie.visibility = View.GONE
                    Glide.with(itemView)
                        .load(article.urlToImage)
                        .into(imageTopHeadline)
                }

                tvTitleTopHeadlines.text = article.title
                tvSourceName.text = article.source?.name
                tvDateNews.visibility = View.GONE
                test.text = article.publishedAt?.let { timeFormatter(it) }
            }
            itemView.setOnClickListener {
                onItemClickCallback.onClicked(article)
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun timeFormatter(date: String): String {
        val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val time = LocalDateTime.parse(date, secondApiFormat)
        val timeNow= LocalDateTime.now()
        val timeCount: String = if(timeNow.dayOfMonth == time.dayOfMonth && timeNow.year == time.year && timeNow.month == time.month){
            "${timeNow.hour - time.hour}hour ago"
        } else if (timeNow.dayOfMonth != time.dayOfMonth && timeNow.month == time.month && timeNow.year == time.year){
            "${timeNow.dayOfMonth - time.dayOfMonth} days ago"
        } else if (timeNow.month != time.month && timeNow.year == time.year){
            "${timeNow.month.value - time.month.value} months ago"
        }else{
            "${timeNow.year - time.year} years ago"
        }
        return timeCount
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TechnologyViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}