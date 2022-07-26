package com.stardeux.upprime.tmdb.credit.repository.api

import com.stardeux.upprime.tmdb.credit.repository.api.model.TmdbCreditContainerResponse
import com.stardeux.upprime.tmdb.video.repository.api.model.TmdbVideoContainerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbCreditApi {

    @GET("movie/{imdbOrTmdbMovieId}/credits")
    suspend fun movieCredits(
        @Path("imdbOrTmdbMovieId") imdbOrTmdbMovieId: String
    ): TmdbCreditContainerResponse

    @GET("tv/{tmdbSeriesId}/credits")
    suspend fun seriesCredits(
        @Path("tmdbSeriesId") tmdbSeriesId: String
    ): TmdbCreditContainerResponse
}