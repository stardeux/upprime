package com.stardeux.upprime.media.fiche.usecase

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.stardeux.upprime.R
import com.stardeux.upprime.media.fiche.ui.model.Illustration
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MediaIllustrationUseCase(private val context: Context) {

    suspend fun getIllustration(mediaFicheUi: MediaFicheUi): Illustration {
        return when {
            mediaFicheUi.backdropUrl?.isNotBlank() == true -> {
                Illustration.Landscape(mediaFicheUi.backdropUrl)
            }
            mediaFicheUi.posterUrl?.isNotBlank() == true -> {
                download(mediaFicheUi.posterUrl)
            }
            else -> {
                Illustration.Error
            }
        }
    }

    private suspend fun download(posterUrl: String): Illustration {
        return suspendCoroutine { cont ->
            Glide.with(context).download(posterUrl).listener(object : RequestListener<File> {

                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<File>?, isFirstResource: Boolean
                ): Boolean {
                    cont.resume(Illustration.Error)
                    return true
                }

                override fun onResourceReady(
                    resource: File,
                    model: Any?,
                    target: Target<File>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val absoluteResourcePath = resource.absolutePath
                    val bitmap = BitmapFactory.decodeFile(absoluteResourcePath)
                    val defaultColor = ContextCompat.getColor(context, R.color.colorPrimary)
                    val mostColor = Palette.from(bitmap).generate().getDominantColor(defaultColor)

                    cont.resume(Illustration.PosterWithBackgroundColor(absoluteResourcePath, mostColor))
                    return true
                }

            }).submit()
        }

    }
}