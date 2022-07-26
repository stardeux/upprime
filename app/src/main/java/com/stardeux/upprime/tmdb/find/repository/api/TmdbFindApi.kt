package com.stardeux.upprime.tmdb.find.repository.api

import com.stardeux.upprime.tmdb.find.repository.model.TmdbFindMediaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbFindApi {

    @GET("find/{imdbId}?external_source=imdb_id")
    suspend fun find(
        @Path("imdbId") imdbId: String, @Query("language") language: String
    ): TmdbFindMediaResponse
}