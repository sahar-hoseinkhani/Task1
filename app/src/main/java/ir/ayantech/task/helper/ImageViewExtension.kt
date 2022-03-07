package ir.ayantech.task.helper

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrl(
    url: String,
    placeHolderResourceId: Int? = null,
    skipMemoryCache: Boolean = true
) {
    val myOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(skipMemoryCache)
    placeHolderResourceId?.let { myOptions.placeholder(it) }
    val builder = Glide.with(context).load(Uri.parse(url)).apply(myOptions)
    builder.into(this)
}