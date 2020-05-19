package com.stardeux.upprime.network.tmdb.di

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type
import java.text.ParseException

class LocalDateJsonDeserializer : JsonDeserializer<LocalDate?> {

    override fun deserialize(
        json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?
    ): LocalDate? {

        return try {
            DateTimeFormatter.ISO_LOCAL_DATE.parse(json.asString, LocalDate::from)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }
}