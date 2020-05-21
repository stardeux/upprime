package com.stardeux.upprime.tmdb.credit.ui.model

import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.credit.usecase.model.Casting
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits

class CreditUiMapper(private val posterMapper: PosterMapper) {

    suspend fun mapToCreditUiList(mediaCredits: MediaCredits): List<CreditUi> {
        val crewUi = mediaCredits.crew.map { mapToCreditUi(it) }
        val castingUi = mediaCredits.casting.map { mapToCreditUi(it) }

        return crewUi.union(castingUi).toList()
    }

    suspend fun mapToCreditUi(crew: Crew): CreditUi {
        return with(crew) {
            CreditUi(name = name,
                role = job,
                posterPath = posterPath?.let { posterMapper.getCompletePosterUrl(it) })
        }
    }

    suspend fun mapToCreditUi(casting: Casting): CreditUi {
        return with(casting) {
            CreditUi(name = name,
                role = character,
                posterPath = posterPath?.let { posterMapper.getCompletePosterUrl(it) })
        }
    }

}