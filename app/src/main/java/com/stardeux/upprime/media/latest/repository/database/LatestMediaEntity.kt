package com.stardeux.upprime.media.latest.repository.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.latest.repository.database.LatestMediaEntity.Companion.TABLE_LATEST_NAME
import org.threeten.bp.LocalDate

@Entity(tableName = TABLE_LATEST_NAME)
data class LatestMediaEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COL_ID) val id: Long,
    @ColumnInfo(name = COL_AMAZON_ID) val amazonWebUrl: String,
    @ColumnInfo(name = COL_TITLE) val title: String?,
    @ColumnInfo(name = COL_IMDB_ID) val imdbId: ImdbId,
    @ColumnInfo(name = COL_DATE_ADDED) val dateAdded: LocalDate,
    @ColumnInfo(name = COL_MEDIA_TYPE) val type: MediaType
) {

    companion object {
        const val TABLE_LATEST_NAME = "latest"
        const val COL_ID = "id"
        const val COL_AMAZON_ID = "amazon_id"
        const val COL_TITLE = "title"
        const val COL_IMDB_ID = "imdb_id"
        const val COL_DATE_ADDED = "date_added"
        const val COL_MEDIA_TYPE = "media_type"
    }
}