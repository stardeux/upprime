package com.stardeux.upprime.tmdb.credit.repository

import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import com.stardeux.upprime.tmdb.series.repository.api.SeriesRemoteDataSource

class SeriesCreatorRepository(
    private val seriesRemoteDataSource: SeriesRemoteDataSource,
    private val mediaCreditMapper: MediaCreditMapper
) {

    suspend fun getCreator(tmdbId: TmdbId, language: String): List<Crew>? {
        return seriesRemoteDataSource.getSeriesDetails(tmdbId, language).createdBy?.map {
            mediaCreditMapper.mapToCrew(it)
        }
    }
}