package com.stardeux.upprime.media.expired.repository.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaEntity.Companion.TABLE_EXPIRED_NAME
import org.threeten.bp.LocalDate

@Entity(tableName = TABLE_EXPIRED_NAME)
data class ExpiredMediaEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COL_ID) val id: Long,
    @ColumnInfo(name = COL_AMAZON_ID) val amazonWebUrl: String,
    @ColumnInfo(name = COL_TITLE) val title: String?,
    @ColumnInfo(name = COL_IMDB_ID) val imdbId: ImdbId,
    @ColumnInfo(name = COL_DATE_ADDED) val dateAdded: LocalDate,
    @ColumnInfo(name = COL_MEDIA_TYPE) val type: MediaType
) {

    companion object {
        const val TABLE_EXPIRED_NAME = "expired"
        const val COL_ID = "id"
        const val COL_AMAZON_ID = "amazon_id"
        const val COL_TITLE = "title"
        const val COL_IMDB_ID = "imdb_id"
        const val COL_DATE_ADDED = "date_added"
        const val COL_MEDIA_TYPE = "media_type"
    }
}