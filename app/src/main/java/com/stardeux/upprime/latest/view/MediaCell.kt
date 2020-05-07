package com.stardeux.upprime.latest.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.latest.ui.model.MediaUi

class MediaCell : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val title: TextView by lazy { findViewById<TextView>(R.id.title) }
    private val gender: TextView by lazy { findViewById<TextView>(R.id.details) }

    init {
        setLayout(R.layout.latest_media_item)
    }

    fun bind(mediaUi: MediaUi) {
        title.text = mediaUi.title
        gender.text = context.getString(mediaUi.type)
    }
}