package com.stardeux.upprime.tmdb.credit.ui.model

import com.stardeux.upprime.tmdb.credit.usecase.model.Casting
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits
import kotlin.math.min

class CreditUiMapper {

    suspend fun mapToCreditUiList(mediaCredits: MediaCredits): CreditsUi {
        val crewUi = mediaCredits.crew.map { mapToCrewUi(it) }
        val castingUi = mediaCredits.casting.map { mapToCastingUi(it) }
            .subList(0, min(3, mediaCredits.casting.size))

        return CreditsUi(crewUi, castingUi)
    }

    fun mapToCrewUi(crew: Crew): CrewUi {
        return with(crew) {
            CrewUi(name = name)
        }
    }

    fun mapToCastingUi(casting: Casting): CastingUi {
        return with(casting) {
            CastingUi(name = name)
        }
    }

}