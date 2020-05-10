package com.stardeux.upprime.amazon.latest.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.amazon.common.view.MediaUi
import com.stardeux.upprime.core.extension.setTextAndVisibility

class MediaItem : CardView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val title: TextView by lazy { findViewById<TextView>(R.id.title) }
    private val firstInfo: TextView by lazy { findViewById<TextView>(R.id.firstInfo) }
    private val secondInfo: TextView by lazy { findViewById<TextView>(R.id.secondInfo) }
    private val thirdInfo: TextView by lazy { findViewById<TextView>(R.id.thirdInfo) }
    private val ratings: TextView by lazy { findViewById<TextView>(R.id.ratings) }
    private val poster: ImageView by lazy { findViewById<ImageView>(R.id.poster) }

    init {
        setLayout(R.layout.item_media)
        initLayout()
    }

    private fun initLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setLayoutParams(layoutParams)
    }

    fun bind(mediaUi: MediaUi) {
        title.setTextAndVisibility(mediaUi.title)
        firstInfo.setTextAndVisibility(computeText(context.getString(mediaUi.mediaTypeStringRes), mediaUi.runtime, " "))
        secondInfo.setTextAndVisibility(computeText(mediaUi.mainNationality, mediaUi.mediaReleaseYear, " "))
        thirdInfo.setTextAndVisibility(mediaUi.mainGenre)
        ratings.setTextAndVisibility(mediaUi.rating)

        with(Glide.with(this)) {
            clear(poster)

            mediaUi.posterUrl?.let {
                load(it).centerCrop().error(R.drawable.ic_error_black_24dp).into(poster)
            }
        }
    }

    private fun computeText(firstText: String?, secondText: String?, separator: String) : String? {
        return if (firstText != null && secondText != null) {
            "$firstText $separator $secondText"
        } else firstText ?: secondText
    }

}