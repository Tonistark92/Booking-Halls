package com.ainshams.graduation_booking_halls.data.remote.dto

import android.net.Uri
import com.google.gson.JsonElement

data class  SignUpResponse(
    val employee_id: Int,
    val employee_name: String,
    val email: String,
    val password: String,
    val phone_num: String,
    val specialization: String,
//    val employee_photo: JsonElement?,
    //Any

)