package com.stardeux.upprime.database.parser

private const val SEPARATOR = "#"

fun parseDatabaseProductionCountries(productionCountries: String): List<String> {
    return productionCountries.split("#")
}

fun formatDatabaseProductionCountries(productionCountries: List<String>): String {
    return productionCountries.joinToString("#")
}