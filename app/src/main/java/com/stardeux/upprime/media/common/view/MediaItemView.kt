package com.stardeux.upprime.media.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.core.extension.setTextAndVisibility
import com.stardeux.upprime.core.mapper.mapMediaTypeToStringId

class MediaItemView : CardView {

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

    fun bind(mediaItemUi: MediaItemUi) {
        title.setTextAndVisibility(mediaItemUi.title)
        firstInfo.setTextAndVisibility(
            computeText(
                context.getString(
                    mapMediaTypeToStringId(
                        mediaItemUi.type
                    )
                ), mediaItemUi.runtime, " "
            )
        )
        secondInfo.setTextAndVisibility(
            computeText(
                mediaItemUi.mainNationality,
                mediaItemUi.mediaReleaseYear,
                " "
            )
        )
        thirdInfo.setTextAndVisibility(mediaItemUi.mainGenre)
        ratings.setTextAndVisibility(mediaItemUi.rating)

        mediaItemUi.posterUrl?.let {
            Glide.with(this).load(it).centerCrop().error(R.drawable.ic_error_black_24dp)
                .into(poster).clearOnDetach()
        }

        setOnClickListener { mediaItemUi.onCardClicked(mediaItemUi) }
    }

    private fun computeText(firstText: String?, secondText: String?, separator: String): String? {
        return if (firstText != null && secondText != null) {
            "$firstText $separator $secondText"
        } else firstText ?: secondText
    }

}