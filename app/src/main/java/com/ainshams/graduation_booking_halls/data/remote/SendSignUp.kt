package com.ainshams.graduation_booking_halls.data.remote

import okhttp3.MultipartBody

data class SendSignUp(
    val  employee_name :String,
    val email :String,
    val  password :String,
    val phone_num :String,
    val  specialization :String,
//    val employee_photo: String,

    )