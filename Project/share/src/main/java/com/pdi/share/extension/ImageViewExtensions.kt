package com.pdi.share.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String, imgLoading: Drawable) {
    Glide
        .with(this.context)
        .load(url)
        //.placeholder(imgLoading)
        .into(this)
}