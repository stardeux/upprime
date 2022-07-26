package com.stardeux.upprime.tmdb.video.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.tmdb.video.ui.MediaVideoItem
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi

class MediaVideoAdapter : RecyclerView.Adapter<MediaVideoViewHolder>() {

    private val videosList = mutableListOf<MediaVideoUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaVideoViewHolder {
        return MediaVideoViewHolder(
            MediaVideoItem(parent.context)
        )
    }

    override fun onBindViewHolder(holder: MediaVideoViewHolder, position: Int) {
        holder.bind(videosList[position])
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    fun submitList(videos: List<MediaVideoUi>) {
        videosList.clear()
        videosList.addAll(videos)
        notifyDataSetChanged()
    }

}