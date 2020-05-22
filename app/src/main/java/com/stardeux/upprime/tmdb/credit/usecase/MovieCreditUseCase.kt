package com.stardeux.upprime.tmdb.credit.usecase

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.repository.MediaCreditRepository
import com.stardeux.upprime.tmdb.credit.usecase.model.Crew
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits
import java.util.*

class MovieCreditUseCase(private val mediaCreditRepository: MediaCreditRepository) : CreditUseCase {

    override suspend fun getMediaCredit(tmdbId: TmdbId): MediaCredits {
        return with(mediaCreditRepository.getCredits(MediaType.MOVIE, tmdbId)) {
            copy(casting = filerCasting(casting), crew = filerCrew(crew))
        }
    }

    private fun filerCrew(crew: List<Crew>): List<Crew> {
        return crew.filter { JOB_KEPT.contains(it.job?.toLowerCase(Locale.ROOT)) }
    }

    companion object {
        private val JOB_KEPT = listOf("director")
    }
}