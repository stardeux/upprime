package com.stardeux.upprime.tmdb.credit.usecase

import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.repository.SeriesCreatorRepository
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import java.util.*

class SeriesCreatorUseCase(
    private val seriesCreatorRepository: SeriesCreatorRepository,
    private val locale: Locale
) {

    suspend fun getCreator(tmdbId: TmdbId): List<Crew>? {
        return seriesCreatorRepository.getCreator(tmdbId, locale.language)
    }
}