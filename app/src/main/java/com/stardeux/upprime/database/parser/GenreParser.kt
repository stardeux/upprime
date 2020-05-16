package com.stardeux.upprime.database.parser

fun getDatabaseGenres(genres: String): List<String> {
    return genres.split("#")
}

