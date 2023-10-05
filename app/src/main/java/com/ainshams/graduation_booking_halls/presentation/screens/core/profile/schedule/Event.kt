package com.ainshams.graduation_booking_halls.presentation.screens.core.profile.schedule

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class Event(
    val course: String? = null,
    val color: Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val hall: String ,
)
//name = "Google I/O Keynote",
//color = Color(0xFFAFBBF2),
//start = LocalDateTime.parse("2021-05-18T09:00:00"),
//end = LocalDateTime.parse("2021-05-18T10:00:00"),
//description = ""
