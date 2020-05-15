package com.stardeux.upprime.tmdb.common.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stardeux.upprime.tmdb.common.model.ProductionCountryEntity.Companion.TABLE_PRODUCTION_COUNTRY_NAME

@Entity(tableName = TABLE_PRODUCTION_COUNTRY_NAME)
class ProductionCountryEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COL_PRODUCTION_COUNTRY_ID) val id: Int = 0,
    @ColumnInfo(name = COL_PRODUCTION_COUNTRY_ISO) val isoCode: String?,
    @ColumnInfo(name = COL_PRODUCTION_COUNTRY_NAME) val name: String?
) {

    companion object {
        const val TABLE_PRODUCTION_COUNTRY_NAME = "production_country"
        const val COL_PRODUCTION_COUNTRY_ID = "production_country_id"
        const val COL_PRODUCTION_COUNTRY_ISO = "production_country_iso"
        const val COL_PRODUCTION_COUNTRY_NAME = "production_country_name"
    }

}