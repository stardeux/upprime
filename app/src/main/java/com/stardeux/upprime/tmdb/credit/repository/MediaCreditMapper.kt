package com.stardeux.upprime.tmdb.credit.repository

import com.stardeux.upprime.tmdb.credit.repository.api.model.TmdbCastResponse
import com.stardeux.upprime.tmdb.credit.repository.api.model.TmdbCreditContainerResponse
import com.stardeux.upprime.tmdb.credit.repository.api.model.TmdbCrewResponse
import com.stardeux.upprime.tmdb.credit.usecase.model.Casting
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits

class MediaCreditMapper {

    suspend fun mapToMediaCredits(tmdbCreditContainerResponse: TmdbCreditContainerResponse) : MediaCredits {
        return with(tmdbCreditContainerResponse) {
            MediaCredits(
                casting = cast?.map { mapToCasting(it) } ?: emptyList(),
                crew = crew?.map { mapToCrew(it) } ?: emptyList()
            )
        }
    }


    fun mapToCasting(creditResponse: TmdbCastResponse) : Casting {
        return with(creditResponse) {
            Casting(
                name = name,
                posterPath = profilePath,
                character = character
            )
        }
    }

    fun mapToCrew(creditResponse: TmdbCrewResponse) : Crew {
        return with(creditResponse) {
            Crew(
                name = name,
                posterPath = profilePath,
                job = job
            )
        }
    }

}