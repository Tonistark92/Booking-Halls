package com.ainshams.graduation_booking_halls.di

import com.ainshams.graduation_booking_halls.data.remote.dto.SignUpResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class SignUpResponseDeserializer : JsonDeserializer<SignUpResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SignUpResponse {
        val jsonObject = json?.asJsonObject
        val employeePhoto = jsonObject?.get("employee_photo") // Access the employee_photo field manually

        return SignUpResponse(
            employee_id = jsonObject?.get("employee_id")?.asInt ?: 0,
            employee_name = jsonObject?.get("employee_name")?.asString ?: "",
            email = jsonObject?.get("email")?.asString ?: "",
            password = jsonObject?.get("password")?.asString ?: "",
            phone_num = jsonObject?.get("phone_num")?.asString ?: "",
            specialization = jsonObject?.get("specialization")?.asString ?: "",
        )
    }
}