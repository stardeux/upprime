package com.stardeux.upprime.search.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.databinding.ItemSearchResultBinding
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUi

class SearchResultView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val binding = ItemSearchResultBinding.inflate(LayoutInflater.from(context), this)

    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun bind(amazonSearchResultUi: AmazonSearchResultUi) {
        binding.searchResultTitle.text = computeTitle(amazonSearchResultUi)
        setOnClickListener { amazonSearchResultUi.onItemClicked(amazonSearchResultUi) }
    }

    private fun computeTitle(amazonSearchResultUi: AmazonSearchResultUi): String {
        return amazonSearchResultUi.title + amazonSearchResultUi.year?.let { " ($it)" }
    }
}