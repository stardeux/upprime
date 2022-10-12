package com.stardeux.upprime.search.view

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.bold
import com.stardeux.upprime.R
import com.stardeux.upprime.databinding.ViewEmptySearchBinding

class EmptySearchResultView : ConstraintLayout {

    private val binding = ViewEmptySearchBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    fun setEmptyQuery(query: String) {
        val emptySearchStr = context.getString(R.string.empty_search_caption)
        val emptyQueryCaption =
            SpannableStringBuilder().append(emptySearchStr).append(" : ").bold { append(query) }

        binding.emptySearchCaption.text = emptyQueryCaption
    }
}