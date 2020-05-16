package com.stardeux.upprime.database.parser

fun getDatabaseProductionCountries(productionCountries: String): List<String> {
    return productionCountries.split("#")
}