package com.stardeux.upprime.network.amazon

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type
import java.text.ParseException

class LocalDateTimeJsonDeserializer : JsonDeserializer<LocalDateTime?> {

    override fun deserialize(
        json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?
    ): LocalDateTime? {

        return try {
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(json.asString, LocalDateTime::from)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }
}