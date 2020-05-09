package com.stardeux.upprime.tmdb.find.repository.api

import com.stardeux.upprime.tmdb.find.repository.model.FindMediaResponse
import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FindApi {

    @GET("find/{imdbId}?external_source=imdb_id")
    suspend fun find(
        @Path("imdbId") imdbId: String, @Query("language") language: String
    ): FindMediaResponse
}