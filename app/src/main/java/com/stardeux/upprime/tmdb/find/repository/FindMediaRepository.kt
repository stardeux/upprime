package com.stardeux.upprime.tmdb.find.repository

import com.stardeux.upprime.tmdb.find.repository.api.TmdbFindApi
import com.stardeux.upprime.tmdb.find.repository.model.TmdbFindMediaResponse

class FindMediaRepository (private val tmdbFindApi: TmdbFindApi) {

    suspend fun findMedia(imdbId: String, language: String): TmdbFindMediaResponse {
        return tmdbFindApi.find(imdbId, language)
    }
}