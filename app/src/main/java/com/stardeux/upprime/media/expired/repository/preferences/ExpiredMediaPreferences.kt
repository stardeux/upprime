package com.stardeux.upprime.media.expired.repository.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class ExpiredMediaPreferences(private val sharedPreferences: SharedPreferences) {

    fun setExpiredMediaHasBeenRequested(date: OffsetDateTime = OffsetDateTime.now()) {
        sharedPreferences.edit {
            putString(
                EXPIRED_MEDIA_REQUEST_DATE, EXPIRED_MEDIA_REQUEST_DATE_FORMATTER.format(date)
            )
        }
    }

    fun getPreviousMediaRequestDate(): OffsetDateTime? {
        val latestMediaRequestDate = sharedPreferences.getString(EXPIRED_MEDIA_REQUEST_DATE, null)
        return latestMediaRequestDate?.let {
            EXPIRED_MEDIA_REQUEST_DATE_FORMATTER.parse(it, OffsetDateTime::from)
        }
    }

    companion object {
        private const val EXPIRED_MEDIA_REQUEST_DATE = "EXPIRED_MEDIA_REQUEST_DATE"
        private val EXPIRED_MEDIA_REQUEST_DATE_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    }

}