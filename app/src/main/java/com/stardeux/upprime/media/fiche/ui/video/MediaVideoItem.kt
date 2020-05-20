package com.stardeux.upprime.media.fiche.ui.video

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import kotlinx.android.synthetic.main.item_video.view.*

class MediaVideoItem : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        setLayout(R.layout.item_video)
        iniLayout()
    }

    private fun iniLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            context.resources.getDimensionPixelOffset(R.dimen.video_item_width),
            context.resources.getDimensionPixelOffset(R.dimen.video_item_height)
        )
        setLayoutParams(layoutParams)
    }

    private fun bind(mediaVideoUi: MediaVideoUi) {
        with(Glide.with(this)) {
            clear(videoImage)
            load(mediaVideoUi.thumbnailUrl).into(videoImage)
        }

        setOnClickListener { mediaVideoUi.onMediaVideoClicked(mediaVideoUi) }
    }
}