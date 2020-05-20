package com.stardeux.upprime.media.fiche.di

import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCase
import com.stardeux.upprime.media.fiche.ui.MediaFicheViewModel
import org.koin.dsl.module

val ficheModule = module {
    factory { provideMediaFicheViewModel(get()) }
}

private fun provideMediaFicheViewModel(getImdbMediaDetailsUseCase: GetImdbMediaDetailsUseCase): MediaFicheViewModel {
    return MediaFicheViewModel(getImdbMediaDetailsUseCase)
}