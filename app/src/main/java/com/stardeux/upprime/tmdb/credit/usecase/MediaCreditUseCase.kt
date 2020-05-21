package com.stardeux.upprime.tmdb.credit.usecase

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.repository.MediaCreditRepository
import com.stardeux.upprime.tmdb.credit.usecase.model.Casting
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits
import kotlin.math.min

class MediaCreditUseCase(private val mediaCreditRepository: MediaCreditRepository) {

    suspend fun getMediaCredit(mediaType: MediaType, tmdbId: TmdbId): MediaCredits {
        return with(mediaCreditRepository.getCredits(mediaType, tmdbId)) {
            copy(casting = filerCasting(casting), crew = filerCrew(crew))
        }
    }

    private fun filerCasting(casting: List<Casting>?): List<Casting>? {
        return casting?.let {
            it.subList(0, min(20, it.size))
        }
    }

    private fun filerCrew(crew: List<Crew>?): List<Crew>? {
        return crew?.let { crewList ->
            crewList.filter { JOB_KEPT.contains(it.job?.toLowerCase()) }
        }
    }

    companion object {
        private val JOB_KEPT = listOf("director")
    }
}