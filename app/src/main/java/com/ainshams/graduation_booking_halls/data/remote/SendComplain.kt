package com.ainshams.graduation_booking_halls.data.remote

data class SendComplain(
    val employee_num_id: String,
    val hall_num: String,
    val text_complain: String,
)
