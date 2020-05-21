package com.stardeux.upprime.tmdb.series.repository.database

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId

class SeriesLocalDataSource (private val seriesDao: SeriesDao) {

    suspend fun getSeries(imdbId: ImdbId): SeriesDetailsEntity? {
        return seriesDao.getSeriesByImdbId(imdbId)
    }

    suspend fun insert(seriesEntity: SeriesDetailsEntity): Long {
        return seriesDao.insert(seriesEntity)
    }

}