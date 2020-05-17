package com.stardeux.upprime.media.fiche.di

import com.stardeux.upprime.media.fiche.ui.MediaFicheViewModel
import org.koin.dsl.module

val ficheModule = module {
    factory { provideMediaFicheViewModel() }
}

private fun provideMediaFicheViewModel(): MediaFicheViewModel {
    return MediaFicheViewModel()
}