package com.stardeux.upprime.tmdb.credit.usecase

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.repository.MediaCreditRepository
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits

class SeriesCreditsUseCase(
    private val mediaCreditRepository: MediaCreditRepository,
    private val seriesCreatorUseCase: SeriesCreatorUseCase
) : CreditUseCase {

    suspend fun getMediaCredit(tmdbId: TmdbId): MediaCredits {
        val credit = mediaCreditRepository.getCredits(MediaType.SERIES, tmdbId)
        val creator = seriesCreatorUseCase.getCreator(tmdbId)

        return with(credit) {
            copy(
                casting = filerCasting(casting),
                crew = creator
            )
        }
    }


}
