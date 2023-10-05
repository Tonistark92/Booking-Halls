package com.ainshams.graduation_booking_halls.presentation.screens.core.profile.schedule

import com.ainshams.graduation_booking_halls.data.remote.dto.ScheduleDto
import com.ainshams.graduation_booking_halls.data.remote.dto.ScheduleDtoItem

data class ScheduleState(
    val schedule: List<ScheduleDtoItem>? = emptyList(),
    val isLoading: Boolean = true,
    var networkError: Boolean = false,
    var notFound: Boolean = false,
)
