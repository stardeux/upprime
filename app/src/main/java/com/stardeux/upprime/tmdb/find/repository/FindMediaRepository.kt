package com.stardeux.upprime.tmdb.find.repository

import com.stardeux.upprime.tmdb.find.repository.api.FindApi
import com.stardeux.upprime.tmdb.find.repository.model.FindMediaResponse

class FindMediaRepository (private val findApi: FindApi) {

    suspend fun findMedia(imdbId: String, language: String): FindMediaResponse {
        return findApi.find(imdbId, language)
    }
}