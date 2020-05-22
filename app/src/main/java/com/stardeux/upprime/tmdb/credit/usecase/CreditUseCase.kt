package com.stardeux.upprime.tmdb.credit.usecase

import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits

interface CreditUseCase {

    suspend fun getMediaCredit(tmdbId: TmdbId): MediaCredits
}