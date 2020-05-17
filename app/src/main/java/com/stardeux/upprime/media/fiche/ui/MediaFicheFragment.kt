package com.stardeux.upprime.media.fiche.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.enumFromOrdinal
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.media.common.usecase.model.Media
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import kotlinx.android.synthetic.main.fragment_media_fiche.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MediaFicheFragment : Fragment(R.layout.fragment_media_fiche) {

    private val mediaFicheViewModel: MediaFicheViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val media: Media = requireNotNull(arguments?.getParcelable(MEDIA_ARG))
        mediaFicheViewModel.load(media)
        mediaFicheViewModel.mediaItemUi.observeNotNull(viewLifecycleOwner, ::bindFiche)
    }

    private fun bindFiche(mediaFicheUi: MediaFicheUi) {
        Glide.with(requireContext()).load(mediaFicheUi.backdropPath).into(mediaCouv)
        mediaTitle.text = mediaFicheUi.title
    }

    companion object {
        private const val MEDIA_ARG = "MEDIA_ARG"

        fun newInstance(media: Media): MediaFicheFragment {
            return MediaFicheFragment().apply {
                arguments = bundleOf(MEDIA_ARG to media)
            }
        }
    }
}