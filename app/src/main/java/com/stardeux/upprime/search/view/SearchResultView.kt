package com.stardeux.upprime.search.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.R
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUi

class SearchResultView : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val searchResultTitle by lazy { findViewById<TextView>(R.id.searchResultTitle) }

    fun bind(amazonSearchResultUi: AmazonSearchResultUi) {
        searchResultTitle.text = computeTitle(amazonSearchResultUi)
    }

    private fun computeTitle(amazonSearchResultUi: AmazonSearchResultUi): String {
        return amazonSearchResultUi.title + amazonSearchResultUi.year?.let { it.toString() }
    }
}