package com.stardeux.upprime.media.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.core.extension.setTextAndVisibility
import com.stardeux.upprime.core.mapper.mapMediaTypeToStringId
import com.stardeux.upprime.databinding.ItemMediaBinding

class MediaItemView : CardView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val binding = ItemMediaBinding.inflate(LayoutInflater.from(context), this)

    init {
        initLayout()
    }

    private fun initLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setLayoutParams(layoutParams)
    }

    fun bind(mediaItemUi: MediaItemUi) {
        binding.title.setTextAndVisibility(mediaItemUi.title)
        binding.firstInfo.setTextAndVisibility(
            computeText(
                context.getString(
                    mapMediaTypeToStringId(
                        mediaItemUi.type
                    )
                ), mediaItemUi.runtime, " "
            )
        )
        binding.secondInfo.setTextAndVisibility(
            computeText(
                mediaItemUi.mainNationality, mediaItemUi.mediaReleaseYear, " "
            )
        )
        binding.thirdInfo.setTextAndVisibility(mediaItemUi.mainGenre)
        binding.ratings.setTextAndVisibility(mediaItemUi.rating)

        Glide.with(this).clear(binding.poster)

        mediaItemUi.posterUrl?.let {
            Glide.with(this).load(it).centerCrop()
                .error(R.drawable.ic_error_black_24dp).into(binding.poster)
        }


        setOnClickListener { mediaItemUi.onCardClicked(mediaItemUi) }
    }

    private fun computeText(firstText: String?, secondText: String?, separator: String): String? {
        return if (firstText != null && secondText != null) {
            "$firstText $separator $secondText"
        } else firstText ?: secondText
    }

}