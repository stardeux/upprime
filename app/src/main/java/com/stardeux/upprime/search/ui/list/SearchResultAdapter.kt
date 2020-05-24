package com.stardeux.upprime.search.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUi
import com.stardeux.upprime.search.view.SearchResultView

class SearchResultAdapter : RecyclerView.Adapter<SearchResultViewHolder>() {

    private val items = mutableListOf<AmazonSearchResultUi>()

    fun submitResults(resultsList: List<AmazonSearchResultUi>) {
        items.clear()
        items.addAll(resultsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(SearchResultView(parent.context))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(items[position])
    }

}