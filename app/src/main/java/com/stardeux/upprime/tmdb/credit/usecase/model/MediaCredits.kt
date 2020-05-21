package com.stardeux.upprime.tmdb.credit.usecase.model

data class MediaCredits(
    val casting: List<Casting>,
    val crew: List<Crew>
)