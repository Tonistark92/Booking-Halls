package com.ainshams.graduation_booking_halls.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CourseDtoItem(

    val course_name: String,
    @SerializedName("course-code")
    val course_code: String
)