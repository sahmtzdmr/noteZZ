package com.sadikahmetozdemir.notezz.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sadikahmetozdemir.notezz.R

fun ImageView.load(isFadeInEnabled: Boolean = true, url: String?) {
    val duration = if (isFadeInEnabled)50 else 0
    Glide
        .with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade(duration))
        .placeholder(R.drawable.ic_baseline_image_24)
        .into(this)
}
