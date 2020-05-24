package com.stardeux.upprime.search.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUi
import com.stardeux.upprime.search.view.SearchResultView

class SearchResultViewHolder(searchResultView: SearchResultView) :
    RecyclerView.ViewHolder(searchResultView) {

    fun bind(searchResultUi: AmazonSearchResultUi) {
        (itemView as SearchResultView).bind(searchResultUi)
    }

}