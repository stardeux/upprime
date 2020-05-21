package com.stardeux.upprime.tmdb.credit.repository

import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.credit.repository.api.model.TmdbCastResponse
import com.stardeux.upprime.tmdb.credit.repository.api.model.TmdbCreditContainerResponse
import com.stardeux.upprime.tmdb.credit.repository.api.model.TmdbCrewResponse
import com.stardeux.upprime.tmdb.credit.usecase.model.Casting
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits

class MediaCreditMapper (private val posterMapper: PosterMapper) {

    suspend fun mapToMediaCredits(tmdbCreditContainerResponse: TmdbCreditContainerResponse) : MediaCredits {
        return with(tmdbCreditContainerResponse) {
            MediaCredits(
                casting = cast?.map { mapToCasting(it) },
                crew = crew?.map { mapToCrew(it) }
            )
        }
    }


    suspend fun mapToCasting(creditResponse: TmdbCastResponse) : Casting {
        return with(creditResponse) {
            Casting(
                name = name,
                posterUrl = profilePath?.let { posterMapper.getCompletePosterUrl(it) },
                role = character
            )
        }
    }

    suspend fun mapToCrew(creditResponse: TmdbCrewResponse) : Crew {
        return with(creditResponse) {
            Crew(
                name = name,
                posterUrl = profilePath?.let { posterMapper.getCompletePosterUrl(it) },
                job = job
            )
        }
    }

}