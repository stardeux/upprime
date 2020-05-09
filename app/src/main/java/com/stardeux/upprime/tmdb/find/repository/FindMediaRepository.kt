package com.stardeux.upprime.tmdb.find.repository

import com.stardeux.upprime.tmdb.find.repository.api.TmdbFindApi
import com.stardeux.upprime.tmdb.find.repository.model.FindMediaResponse

class FindMediaRepository (private val tmdbFindApi: TmdbFindApi) {

    suspend fun findMedia(imdbId: String, language: String): FindMediaResponse {
        return tmdbFindApi.find(imdbId, language)
    }
}