package com.example.nwavetask.DownloadImg

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.nwavetask.R

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context).load(imageUrl).placeholder(R.drawable.no_img).into(view)
}