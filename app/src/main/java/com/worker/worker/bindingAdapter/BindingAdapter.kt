package com.worker.worker.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.worker.worker.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)

        .load(url)
        .into(view)
}

@BindingAdapter("profileUrl")
fun loadImageProfile(view: ImageView, url: String) {
    Glide.with(view.context)
        .applyDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.person)
                .error(R.drawable.person)
        )
        .load(url)
        .into(view)
}