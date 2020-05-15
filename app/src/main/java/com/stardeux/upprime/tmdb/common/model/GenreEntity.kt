package com.stardeux.upprime.tmdb.common.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stardeux.upprime.tmdb.common.model.GenreEntity.Companion.TABLE_GENRE_NAME

@Entity(tableName = TABLE_GENRE_NAME)
data class GenreEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COL_GENRE_ID) val id: Int = 0,
    @ColumnInfo(name = COL_GENRE_TMDB_ID) val tmdbId: String?,
    @ColumnInfo(name = COL_GENRE_NAME) val name: String?
) {

    companion object {
        const val TABLE_GENRE_NAME = "genres"
        const val COL_GENRE_ID = "genre_id"
        const val COL_GENRE_TMDB_ID = "genre_tmdb_id"
        const val COL_GENRE_NAME = "genre_name"
    }

}