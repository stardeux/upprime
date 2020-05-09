package com.stardeux.upprime.latest.ui.model

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stardeux.upprime.core.extension.createAsyncDifferConfig
import com.stardeux.upprime.latest.view.MediaItem

class MediaAdapter : ListAdapter<MediaUi, MediaViewHolder>(createAsyncDifferConfig(MediaUiDiffUtil())) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        return MediaViewHolder(MediaItem(parent.context))
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}