package com.stardeux.upprime.search.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUi

class SearchResultView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val searchResultTitle by lazy { findViewById<TextView>(R.id.searchResultTitle) }

    init {
        setLayout(R.layout.item_search_result)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun bind(amazonSearchResultUi: AmazonSearchResultUi) {
        searchResultTitle.text = computeTitle(amazonSearchResultUi)
        setOnClickListener { amazonSearchResultUi.onItemClicked(amazonSearchResultUi) }
    }

    private fun computeTitle(amazonSearchResultUi: AmazonSearchResultUi): String {
        return amazonSearchResultUi.title + amazonSearchResultUi.year?.let { " ($it)" }
    }
}