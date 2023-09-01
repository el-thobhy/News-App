package com.elthobhy.newsapp.ui.home

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.ItemTopHeadlinesBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HeadlineAdapter: RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder>(){
    private val list = ArrayList<Domain>()
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
        fun onItemClicked(data: Domain)
    }

    inner class HeadlineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemTopHeadlinesBinding.bind(itemView)
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(headlineEntity: Domain) {
            binding.apply {
                if(headlineEntity.urlToImage.isNullOrEmpty()){
                    imageTopHeadline.visibility=View.GONE
                    lottie.visibility = View.VISIBLE
                }
                else{
                    lottie.visibility=View.GONE
                    imageTopHeadline.visibility=View.VISIBLE
                    Glide.with(itemView)
                    .load(headlineEntity.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageTopHeadline)
                }
                tvTitleTopHeadlines.text = headlineEntity.title
                tvSourceName.text = headlineEntity.source?.name
                test.text=headlineEntity.publishedAt?.let { timeFormatter(date = it) }.toString()
                tvDateNews.visibility = View.GONE
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(headlineEntity)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun timeFormatter(date: String): String? {
        val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val time =LocalDateTime.parse(date, secondApiFormat)
        val timeNow=LocalDateTime.now()
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
    override fun onBindViewHolder(holder: HeadlineAdapter.HeadlineViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    fun setList(headlineEntity: List<Domain>){
        list.addAll(headlineEntity)
        notifyDataSetChanged()
    }
}