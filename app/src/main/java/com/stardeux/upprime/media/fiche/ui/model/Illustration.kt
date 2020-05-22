package com.stardeux.upprime.media.fiche.ui.model

import androidx.annotation.ColorInt

sealed class Illustration {
    class Landscape(val backdropUrl: String) : Illustration()
    class PosterWithBackgroundColor(
        val posterFilePath: String, @ColorInt val color: Int
    ) : Illustration()

    object Error : Illustration()
}