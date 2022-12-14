package com.codenome.cstv.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.codenome.cstv.model.Player

fun glideRequestListener(progressImage: ProgressBar) =
    object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            progressImage.visibility = View.GONE
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            progressImage.visibility = View.GONE
            return false
        }
    }

fun getPlayersPlaceholder(): MutableList<Player> {
    val playerPlaceholder = mutableListOf<Player>()
    repeat(5) {
        playerPlaceholder.add(Player(id = it))
    }
    return playerPlaceholder
}