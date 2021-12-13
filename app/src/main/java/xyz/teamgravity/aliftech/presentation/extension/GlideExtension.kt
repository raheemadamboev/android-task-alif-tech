package xyz.teamgravity.aliftech.presentation.extension

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import xyz.teamgravity.aliftech.R

fun RequestManager.centerCrop(url: String?, imageView: ImageView) {
    load(url)
        .centerCrop()
        .error(R.drawable.placeholder_error)
        .placeholder(R.drawable.placeholder_image)
        .into(imageView)
}