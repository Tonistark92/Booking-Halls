package com.ainshams.graduation_booking_halls.data.remote

import java.time.LocalDate
import java.time.LocalTime

data class SendRequest(
   val employee_num_id:String,
    val hall_num:String,
   val  booking_day: String,
   val start_time_booking: String,
   val end_time_booking: String,
   val permental:String,
   val text:String,
   val code:String,
)
