package com.ainshams.graduation_booking_halls.data.remote.dto

data class RequestResponse(
    val booking_day: String,
    val employee_num_id: String,
    val end_time_booking: String,
    val hall_num: String,
    val permental: String,
    val request_num_id: Int,
    val start_time_booking: String,
    val text: String
)