package com.stardeux.upprime.tmdb.credit.usecase

import com.stardeux.upprime.tmdb.credit.usecase.model.Casting
import kotlin.math.min

interface CreditUseCase {

    fun filerCasting(casting: List<Casting>?): List<Casting>? {
        return casting?.let {
            it.subList(0, min(MAX_CAST_MEMBER, it.size))
        }
    }

    companion object {
        const val MAX_CAST_MEMBER = 20
    }

}