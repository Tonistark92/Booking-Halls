package com.ainshams.graduation_booking_halls.data.remote.dto

data class NewsResponseItem(
    val booking_day: String,
    val code: Any,
    val date_time_send: String,
    val employee_email: String,
    val employee_name: String,
    val employee_num_id: Int,
    val end_time_booking: String,
    val hall_num: Int,
    val is_seen: Int,
    val permental: Int,
    val request_num_id: Int,
    val respond_agreement: String,
    val respond_state: Int,
    val start_time_booking: String,
    val text: String,
    val update_request: String
)