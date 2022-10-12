package com.stardeux.upprime.tmdb.video.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.stardeux.upprime.R
import com.stardeux.upprime.databinding.ItemVideoBinding
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi

class MediaVideoItem : ConstraintLayout {

    private val binding = ItemVideoBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        initLayout()
    }

    private fun initLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            context.resources.getDimensionPixelOffset(R.dimen.video_item_width),
            context.resources.getDimensionPixelOffset(R.dimen.video_item_height)
        )
        setLayoutParams(layoutParams)
    }

    fun bind(mediaVideoUi: MediaVideoUi) {
        val videoCornerRadius =
            context.resources.getDimensionPixelOffset(R.dimen.video_corner_radius)
        Glide.with(this).load(mediaVideoUi.thumbnailUrl)
            .transform(RoundedCorners(videoCornerRadius), CenterInside()).into(binding.videoImage)
            .clearOnDetach()

        setOnClickListener { mediaVideoUi.onMediaVideoClicked(mediaVideoUi) }
    }
}