package com.stardeux.upprime.media.fiche.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.exhaustive
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.ui.SpacesItemDecoration
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.model.Illustration
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.tmdb.credit.ui.model.CreditsUi
import com.stardeux.upprime.tmdb.video.ui.list.MediaVideoAdapter
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi
import kotlinx.android.synthetic.main.fragment_media_fiche.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MediaFicheFragment : Fragment(R.layout.fragment_media_fiche) {

    private val mediaFicheViewModel: MediaFicheViewModel by viewModel{ parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mediaFicheViewModel.trackScreen(requireActivity())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fiche_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.item_share_media) {
            mediaFicheViewModel.onShareClicked()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mediaVideos) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = MediaVideoAdapter()
            addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelOffset(R.dimen.video_list_item_margin)))
        }

        fab.setOnClickListener { mediaFicheViewModel.onFabClicked() }

        with(mediaFicheViewModel) {
            load()
            mediaItemUi.observeNotNull(viewLifecycleOwner, ::bindFiche)
            videos.observeNotNull(viewLifecycleOwner, ::bindVideos)
            events.observeNotNull(viewLifecycleOwner, ::onFicheClickEvent)
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


    private fun onFicheClickEvent(event: MediaFicheViewModel.Event) {
        when (event) {
            is MediaFicheViewModel.Event.VideoClicked -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(event.mediaVideoUi.videoUrl)))
            }
            is MediaFicheViewModel.Event.ShareClicked -> {
                shareMedia(event.shortMedia)
            }
            is MediaFicheViewModel.Event.PlayClicked -> {
                startActivity(Intent(Intent.ACTION_VIEW, event.mediaFicheUi.amazonPlayUri))
            }
        }.exhaustive
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

            handleVisibilityWithText(getDetails(mediaFicheUi), mediaDetails)
            handleVisibilityWithText(tmdbRating, mediaRatings)
            handleVisibilityWithText(synopsis, mediaSynopsis, mediaSynopsisTitle)
            handleVisibilityWithText(mediaFicheUi.genres.joinToString(", "), mediaGenres)
        }
    }

    private fun getDetails(mediaFicheUi: MediaFicheUi): String {
        val isReleaseYearKnown = mediaFicheUi.mediaReleaseYear?.isNotBlank() == true
        val isRuntimeKnown = mediaFicheUi.runtime?.isNotBlank() == true

        return if (isReleaseYearKnown && isRuntimeKnown) {
            mediaFicheUi.mediaReleaseYear + " - " + mediaFicheUi.runtime
        } else if (isReleaseYearKnown) {
            mediaFicheUi.mediaReleaseYear!!
        } else if (isRuntimeKnown) {
            mediaFicheUi.runtime!!
        } else {
            ""
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


    private fun shareMedia(shortMedia: ShortMedia) {
        val appName = getString(R.string.app_name)
        val packageName = requireContext().packageName
        val playStoreUri = "market://details?id=$packageName"

        val shareTitle = getString(R.string.share_fiche_media_title)
        val shareText =
            getString(R.string.share_fiche_media_content, shortMedia.title, appName, playStoreUri)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, shareTitle)
        startActivity(shareIntent)
    }

    fun getShortMedia(): ShortMedia {
        return requireNotNull(arguments?.getParcelable(MEDIA_ARG))
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