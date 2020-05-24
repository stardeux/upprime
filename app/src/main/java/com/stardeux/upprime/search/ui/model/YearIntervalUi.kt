package com.stardeux.upprime.search.ui.model

import org.threeten.bp.LocalDateTime

data class YearIntervalUi(
    val yearStart: Int,
    val yearEnd : Int,
    val selectedYear: Int
) {
    companion object {

        private val MAX_YEAR = LocalDateTime.now().year
        private const val MIN_YEAR = 1900

        fun defaultYearInterval(selectedYear: Int = MIN_YEAR) : YearIntervalUi {
            return YearIntervalUi(MIN_YEAR, MAX_YEAR, selectedYear)
        }

    }
}