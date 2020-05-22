package com.stardeux.upprime.network.amazon

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.stardeux.upprime.core.model.MediaType
import java.lang.reflect.Type

class MediaTypeJsonDeserializer : JsonDeserializer<MediaType?> {

    override fun deserialize(
        json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?
    ): MediaType? {

        return when (json.asString) {
            "Movie" -> MediaType.MOVIE
            "TV" -> MediaType.SERIES
            else -> null
        }
    }
}