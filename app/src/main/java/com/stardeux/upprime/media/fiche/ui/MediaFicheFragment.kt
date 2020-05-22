package com.stardeux.upprime.media.fiche.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.tmdb.credit.ui.model.CreditsUi
import com.stardeux.upprime.tmdb.video.ui.list.MediaVideoAdapter
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi
import kotlinx.android.synthetic.main.fragment_media_fiche.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MediaFicheFragment : Fragment(R.layout.fragment_media_fiche) {

    private val mediaFicheViewModel: MediaFicheViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mediaVideos) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = MediaVideoAdapter()
        }

        val shortMedia: ShortMedia = requireNotNull(arguments?.getParcelable(MEDIA_ARG))
        mediaFicheViewModel.load(shortMedia)
        mediaFicheViewModel.mediaItemUi.observeNotNull(viewLifecycleOwner, ::bindFiche)
        mediaFicheViewModel.videos.observeNotNull(viewLifecycleOwner, ::bindVideos)
        mediaFicheViewModel.videoClicked.observeNotNull(viewLifecycleOwner, ::onVideoClicked)
        mediaFicheViewModel.credits.observeNotNull(viewLifecycleOwner, ::bindCredits)
    }

    private fun onVideoClicked(mediaVideoUi: MediaVideoUi) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mediaVideoUi.videoUrl)))
    }

    private fun bindCredits(mediaCreditsList: CreditsUi) {
        mediaActors.text = mediaCreditsList.casting.map { it.name }.joinToString(",  ")
        mediaResponsible.text = mediaCreditsList.crew.map { it.name }.joinToString(",  ")
    }


    private fun bindVideos(mediaVideosList: List<MediaVideoUi>) {
        (mediaVideos.adapter as MediaVideoAdapter).submitList(mediaVideosList)
    }

    private fun bindFiche(mediaFicheUi: MediaFicheUi) {
        with(mediaFicheUi) {
            Glide.with(requireContext()).load(backdropUrl).centerCrop().into(mediaCouv)

            mediaResponsibleTitle.text = getString(
                when (mediaFicheUi.type) {
                    MediaType.MOVIE -> R.string.credits_movie_director_title
                    MediaType.SERIES -> R.string.credits_series_creators_title
                }
            )
            mediaTitle.text = title
            mediaDetails.text = runtime
            mediaRatings.text = tmdbRating
            mediaSynopsis.text = synopsis
            mediaGenres.text = mediaFicheUi.genres?.joinToString(" ")
        }
    }

    companion object {
        private const val MEDIA_ARG = "MEDIA_ARG"

        fun newInstance(shortMedia: ShortMedia): MediaFicheFragment {
            return MediaFicheFragment().apply {
                arguments = bundleOf(MEDIA_ARG to shortMedia)
            }
        }
    }
}