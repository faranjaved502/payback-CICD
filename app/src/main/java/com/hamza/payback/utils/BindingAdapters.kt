package com.hamza.payback.utils

import android.widget.*
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @BindingAdapter("load_image")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        if (url != null && url.isNotEmpty()) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }
}