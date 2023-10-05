package com.ainshams.graduation_booking_halls.data.remote

data class MyResponse(
    val employee_id: Int,
    val employee_name: String,
    val email: String,
    val password: String,
    val phone_num: String,
    val specialization: String,
    val concatenated_data: String,
    val employee_photo: String,
)