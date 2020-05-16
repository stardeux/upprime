package com.stardeux.upprime.database.parser

private const val SEPARATOR = "#"

fun parseDatabaseGenres(genres: String): List<String> {
    return genres.split(SEPARATOR)
}

fun formatDatabaseGenres(genres: List<String>): String {
    return genres.joinToString(SEPARATOR)
}

