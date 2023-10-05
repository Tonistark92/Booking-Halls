package com.ainshams.graduation_booking_halls.di

import android.net.Uri
import com.google.gson.*
import java.lang.reflect.Type

class UriDeserializer : JsonDeserializer<Uri> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Uri {
        val imageUrl = json?.asString ?: ""
        return Uri.parse(imageUrl)
    }
}
