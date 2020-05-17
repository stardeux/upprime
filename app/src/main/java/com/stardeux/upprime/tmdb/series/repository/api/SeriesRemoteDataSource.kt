package com.stardeux.upprime.tmdb.series.repository.api

class SeriesRemoteDataSource(
    private val tmdbSeriesApi: TmdbSeriesApi
) {
    suspend fun getSeriesDetails(
        imdbOrTmdbMovieId: String, language: String
    ): TmdbSeriesDetailsResponse {
        return tmdbSeriesApi.seriesDetails(imdbOrTmdbMovieId, language)
    }
}