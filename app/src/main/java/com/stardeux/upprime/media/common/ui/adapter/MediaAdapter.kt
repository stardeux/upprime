package com.stardeux.upprime.media.common.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.core.extension.createAsyncDifferConfig
import com.stardeux.upprime.core.extension.exhaustive
import com.stardeux.upprime.media.common.ui.model.DateSeparatorUi
import com.stardeux.upprime.media.common.ui.model.MediaUi
import com.stardeux.upprime.media.common.view.DateSeparatorItemView
import com.stardeux.upprime.media.common.view.MediaItemView

class MediaAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(
    createAsyncDifferConfig(
        MediaAdapterDiffUtil()
    )
) {

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is MediaUi -> MEDIA_UI_VIEW_TYPE
            is DateSeparatorUi -> DATE_SEPARATOR_UI_VIEW_TYPE
            else -> throw IllegalStateException("Unknown view type for ${item.javaClass.name}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MEDIA_UI_VIEW_TYPE -> MediaViewHolder(MediaItemView(parent.context))
            DATE_SEPARATOR_UI_VIEW_TYPE -> DateSeparatorViewHolder(DateSeparatorItemView(parent.context))
            else -> throw IllegalStateException("Unknown view type for $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val itemViewType = getItemViewType(position)) {
            MEDIA_UI_VIEW_TYPE -> (holder as MediaViewHolder).bind(getItem(position) as MediaUi)
            DATE_SEPARATOR_UI_VIEW_TYPE -> (holder as DateSeparatorViewHolder).bind(getItem(position) as DateSeparatorUi)
            else -> throw IllegalStateException("Unknown view type for $itemViewType")
        }.exhaustive
    }

    companion object {
        private const val MEDIA_UI_VIEW_TYPE = 54352
        private const val DATE_SEPARATOR_UI_VIEW_TYPE = 73345
    }


}