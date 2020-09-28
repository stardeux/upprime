package com.stardeux.upprime.search.view

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.bold
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import kotlinx.android.synthetic.main.view_empty_search.view.*

class EmptySearchResultView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        setLayout(R.layout.view_empty_search)
    }

    fun setEmptyQuery(query: String) {
        val emptySearchStr = context.getString(R.string.empty_search_caption)
        val emptyQueryCaption =
            SpannableStringBuilder().append(emptySearchStr).append(" : ").bold { append(query) }

        emptySearchCaption.text = emptyQueryCaption
    }
}