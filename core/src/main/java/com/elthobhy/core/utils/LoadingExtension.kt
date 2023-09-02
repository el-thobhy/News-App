package com.elthobhy.core.utils


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.elthobhy.core.databinding.DialogAnimationLayoutBinding
import com.facebook.shimmer.ShimmerFrameLayout

fun Boolean.loadingExtension(shimmer: ShimmerFrameLayout?, rv: RecyclerView?=null) {
    if (this) {
        shimmer?.visibility = View.VISIBLE
        rv?.visibility = View.GONE
    }
    else {
        shimmer?.visibility = View.GONE
        rv?.visibility = View.VISIBLE
    }
}
fun showDialogAnimation(context: Context, state: String, message: String? = null, animation: String): AlertDialog{
    val dialogView = DialogAnimationLayoutBinding.inflate(LayoutInflater.from(context))
    dialogView.animationLottie.setAnimation(animation)
    dialogView.tvMessage.text = message
    dialogView.tvEmpty.text = state
    val alert = AlertDialog
        .Builder(context)
        .setView(dialogView.root)
        .setCancelable(true)
        .create()
    alert.window?.decorView?.setBackgroundResource(android.R.color.transparent)
    return alert
}

fun dialogLoading(context: Context) = showDialogAnimation(
    context = context,
    state = "Please Wait",
    animation = "status_loading.json"
)
fun dialogError(e: String?, context: Context) =
    showDialogAnimation(
        context = context,
        message = e,
        state = "Error",
        animation = "status_error.json")