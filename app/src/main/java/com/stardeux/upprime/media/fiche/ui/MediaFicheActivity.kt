package com.stardeux.upprime.media.fiche.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.enumFromOrdinal
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest

class MediaFicheActivity : AppCompatActivity(R.layout.activity_media_fiche) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imdbMediaRequest: ImdbMediaRequest =
            requireNotNull(intent.getParcelableExtra(IMDB_MEDIA_REQUEST_ARG))

        val mediaTypeOrdinal =
            requireNotNull(intent.getIntExtra(MEDIA_TYPE_ARG, -1).takeIf { it != -1 })

        val mediaType = requireNotNull(enumFromOrdinal<MediaType>(mediaTypeOrdinal))

        supportFragmentManager.commit {
            replace(R.id.ficheContent, MediaFicheFragment.newInstance(imdbMediaRequest, mediaType))
        }

    }

    companion object {
        private const val IMDB_MEDIA_REQUEST_ARG = "IMDB_MEDIA_REQUEST_ARG"
        private const val MEDIA_TYPE_ARG = "MEDIA_TYPE_ARG"

        fun newIntent(
            context: Context, imdbMediaRequest: ImdbMediaRequest, mediaType: MediaType
        ): Intent {
            return Intent(context, MediaFicheActivity::class.java).apply {
                putExtra(IMDB_MEDIA_REQUEST_ARG, imdbMediaRequest)
                putExtra(MEDIA_TYPE_ARG, mediaType.ordinal)
            }
        }
    }
}