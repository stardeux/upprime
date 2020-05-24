package com.stardeux.upprime.search.ui.model

import org.threeten.bp.LocalDateTime

data class YearInterval(
    val yearStart: Int,
    val yearEnd : Int
) {
    companion object {

        private val MAX_YEAR = LocalDateTime.now().year
        private const val MIN_YEAR = 1900

        fun defaultYearInterval() : YearInterval {
            return YearInterval(MIN_YEAR, MAX_YEAR)
        }

    }
}