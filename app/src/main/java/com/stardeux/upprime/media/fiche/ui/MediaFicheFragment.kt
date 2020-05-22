package com.stardeux.upprime.media.fiche.ui

import android.R.attr.bitmap
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.PaletteAsyncListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.exhaustive
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.extension.setTextAndVisibility
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.model.Illustration
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.tmdb.credit.ui.model.CreditsUi
import com.stardeux.upprime.tmdb.video.ui.list.MediaVideoAdapter
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi
import kotlinx.android.synthetic.main.fragment_media_fiche.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File


class MediaFicheFragment : Fragment(R.layout.fragment_media_fiche) {

    private val mediaFicheViewModel: MediaFicheViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mediaVideos) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = MediaVideoAdapter()
        }

        val shortMedia: ShortMedia = requireNotNull(arguments?.getParcelable(MEDIA_ARG))

        with(mediaFicheViewModel) {
            load(shortMedia)
            mediaItemUi.observeNotNull(viewLifecycleOwner, ::bindFiche)
            videos.observeNotNull(viewLifecycleOwner, ::bindVideos)
            videoClicked.observeNotNull(viewLifecycleOwner, ::onVideoClicked)
            credits.observeNotNull(viewLifecycleOwner, ::bindCredits)
            illustration.observeNotNull(viewLifecycleOwner, ::onIllustration)
        }
    }

    private fun onIllustration(illustration: Illustration) {
        when (illustration) {
            is Illustration.PosterWithBackgroundColor -> {
                mediaCouv.setBackgroundColor(illustration.color)
                mediaPoster.setImageBitmap(BitmapFactory.decodeFile(illustration.posterFilePath))
            }
            is Illustration.Landscape -> {
                Glide.with(this).load(illustration.backdropUrl).centerCrop().into(mediaCouv)
            }
            Illustration.Error -> {
                mediaCouv.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorPrimary
                    )
                )
            }
        }
    }


    private fun onVideoClicked(mediaVideoUi: MediaVideoUi) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mediaVideoUi.videoUrl)))
    }

    private fun bindCredits(mediaCreditsList: CreditsUi) {
        handleVisibilityWithText(
            mediaCreditsList.casting.map { it.name }.joinToString(",  "),
            mediaActors,
            mediaActorsTitle
        )

        handleVisibilityWithText(
            mediaCreditsList.crew.map { it.name }.joinToString(",  "),
            mediaResponsible,
            mediaResponsibleTitle
        )
    }


    private fun bindVideos(mediaVideosList: List<MediaVideoUi>) {
        val visibility = mediaVideosList.isNotEmpty()

        mediaVideos.isVisible = visibility
        mediaVideoTitle.isVisible = visibility
        (mediaVideos.adapter as MediaVideoAdapter).submitList(mediaVideosList)
    }

    private fun bindFiche(mediaFicheUi: MediaFicheUi) {
        with(mediaFicheUi) {
            mediaResponsibleTitle.text = getString(
                when (mediaFicheUi.type) {
                    MediaType.MOVIE -> R.string.credits_movie_director_title
                    MediaType.SERIES -> R.string.credits_series_creators_title
                }
            )
            mediaTitle.text = title
            mediaDetails.text = runtime

            handleVisibilityWithText(tmdbRating, mediaRatings)
            handleVisibilityWithText(synopsis, mediaSynopsis, mediaSynopsisTitle)
            handleVisibilityWithText(mediaFicheUi.genres.joinToString(" "), mediaGenres)
        }
    }

    private fun handleVisibilityWithText(
        text: String?, destinationTextView: TextView, titleTextView: TextView? = null
    ) {
        val computedVisibility = text?.isNotBlank() == true
        destinationTextView.isVisible = computedVisibility
        titleTextView?.isVisible = computedVisibility
        destinationTextView.text = text
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