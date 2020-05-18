package com.stardeux.upprime.media.latest.repository.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class LatestMediaPreferences(private val sharedPreferences: SharedPreferences) {

    fun setLatestMediaHasBeenRequested(date: OffsetDateTime = OffsetDateTime.now()) {
        sharedPreferences.edit {
            putString(
                LATEST_MEDIA_REQUEST_DATE, LATEST_MEDIA_REQUEST_DATE_FORMATTER.format(date)
            )
        }
    }

    fun getLatestMediaRequestDate(): OffsetDateTime? {
        val latestMediaRequestDate = sharedPreferences.getString(LATEST_MEDIA_REQUEST_DATE, null)
        return latestMediaRequestDate?.let {
            LATEST_MEDIA_REQUEST_DATE_FORMATTER.parse(it, OffsetDateTime::from)
        }
    }

    companion object {
        private const val LATEST_MEDIA_REQUEST_DATE = "LATEST_MEDIA_REQUEST_DATE"
        private val LATEST_MEDIA_REQUEST_DATE_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    }

}