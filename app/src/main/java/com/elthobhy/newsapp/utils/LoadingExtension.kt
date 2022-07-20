package com.elthobhy.newsapp.utils


import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout

fun Boolean.loadingExtension(shimmer: ShimmerFrameLayout?, rv: RecyclerView?) {
    if (this) {
        shimmer?.visibility = View.VISIBLE
        rv?.visibility = View.VISIBLE
    }
    else shimmer?.visibility = View.GONE
}

fun Boolean.loadingDetail(progressBar: ProgressBar?, shimmer: ShimmerFrameLayout?){
    if (this) {
        progressBar?.visibility = View.VISIBLE
        shimmer?.visibility = View.VISIBLE
    } else {
        progressBar?.visibility = View.GONE
        shimmer?.visibility = View.GONE
    }
}