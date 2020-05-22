package com.stardeux.upprime.tmdb.credit.ui.model

import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.credit.usecase.model.Casting
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits

class CreditUiMapper(private val posterMapper: PosterMapper) {

    suspend fun mapToCreditUiList(mediaCredits: MediaCredits): CreditsUi {
        val crewUi = mediaCredits.crew.map { mapToCreditUi(it) }
        val castingUi = mediaCredits.casting.map { mapToCreditUi(it) }

        return CreditsUi(crewUi, castingUi)
    }

    fun mapToCreditUi(crew: Crew): CrewUi {
        return with(crew) {
            CrewUi(name = name)
        }
    }

    fun mapToCreditUi(casting: Casting): CastingUi {
        return with(casting) {
            CastingUi(name = name)
        }
    }

}