package com.stardeux.upprime.latest.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.latest.ui.model.MediaUi

class MediaItem : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val title: TextView by lazy { findViewById<TextView>(R.id.title) }
    private val gender: TextView by lazy { findViewById<TextView>(R.id.details) }
    private val poster: ImageView by lazy { findViewById<ImageView>(R.id.poster) }

    init {
        setLayout(R.layout.latest_media_item)
        initLayout()
    }

    private fun initLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setLayoutParams(layoutParams)
    }

    fun bind(mediaUi: MediaUi) {
        title.text = mediaUi.title
        gender.text = context.getString(mediaUi.mediaTypeStringRes)

        with(Glide.with(this)) {
            clear(poster)

            mediaUi.posterUrl?.let {
                load(it).error(R.drawable.ic_error_black_24dp).into(poster)
            }
        }
    }
}