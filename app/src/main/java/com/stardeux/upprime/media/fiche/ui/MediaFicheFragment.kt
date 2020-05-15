package com.stardeux.upprime.media.fiche.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.stardeux.upprime.R
import com.stardeux.upprime.media.common.ui.model.MediaUi

class MediaFicheFragment : Fragment(R.layout.fragment_media_fiche) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun bindFiche(mediaUi: MediaUi) {

    }

    companion object {
        private const val MEDIA_UI_ARG = "MEDIA_UI_ARG"

        fun newInstance(mediaUi: MediaUi): MediaFicheFragment {
            return MediaFicheFragment().apply {
                arguments = bundleOf(MEDIA_UI_ARG to mediaUi)
            }
        }
    }
}