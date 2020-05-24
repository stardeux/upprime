package com.stardeux.upprime.search.ui.model

import org.threeten.bp.LocalDateTime

data class YearIntervalUi(
    val yearStart: Int,
    val yearEnd : Int,
    val selectedYear: Int
) {
    companion object {

        val MAX_YEAR = LocalDateTime.now().year
        const val MIN_YEAR = 1900

        fun defaultYearInterval(selectedYear: Int) : YearIntervalUi {
            return YearIntervalUi(MIN_YEAR, MAX_YEAR, selectedYear)
        }

    }
}