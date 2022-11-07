package com.example.pazarama_bitirme_app.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pazarama_bitirme_app.data.remote.utils.Constants

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load("$url")
            .into(view)
    }
}