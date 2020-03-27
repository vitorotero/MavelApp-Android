package br.com.tecapp.mavelapp.shared.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun AppCompatImageView.glide(url: String, progressView: ProgressBar? = null) {
    val imageView = this
    progressView?.let {
        it.visibility = View.VISIBLE
    }
    Glide.with(imageView.context)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressView?.let {
                    it.visibility = View.GONE
                }

                imageView.setImageDrawable(resource!!)
                return true
            }
        })
        .centerCrop()
        .into(imageView)
}

fun AppCompatImageView.glideCircular(url: String, progressView: ProgressBar? = null) {
    val imageView = this
    progressView?.let {
        it.visibility = View.VISIBLE
    }
    Glide.with(imageView.context)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressView?.let {
                    it.visibility = View.GONE
                }

                imageView.setImageDrawable(resource!!)
                return true
            }
        })
        .centerCrop()
        .apply(RequestOptions.circleCropTransform())
        .into(imageView)
}