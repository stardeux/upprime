package com.stardeux.upprime.media.fiche.ui.video

import androidx.recyclerview.widget.RecyclerView

class MediaVideoViewHolder(mediaVideoUi: MediaVideoItem) : RecyclerView.ViewHolder(mediaVideoUi) {

    fun bind(mediaVideoUi: MediaVideoUi) {
        (itemView as MediaVideoItem).bind(mediaVideoUi)
    }

}