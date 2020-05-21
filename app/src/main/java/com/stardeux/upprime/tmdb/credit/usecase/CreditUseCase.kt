package com.stardeux.upprime.tmdb.credit.usecase

import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.usecase.model.Casting
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits
import kotlin.math.min

interface CreditUseCase {

    suspend fun getMediaCredit(tmdbId: TmdbId): MediaCredits

    fun filerCasting(casting: List<Casting>?): List<Casting>? {
        return casting?.let {
            it.subList(0, min(MAX_CAST_MEMBER, it.size))
        }
    }

    companion object {
        const val MAX_CAST_MEMBER = 20
    }

}