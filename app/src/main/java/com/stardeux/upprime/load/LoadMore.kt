package com.stardeux.upprime.load

import org.threeten.bp.LocalDate

interface LoadMore {

    fun getNextRequestPage(): Int
    fun isTodayRequestDone() : Boolean
    fun getCompleteInterval(): Pair<LocalDate, LocalDate>
    fun updateCompleteInterval()
    fun request()
}