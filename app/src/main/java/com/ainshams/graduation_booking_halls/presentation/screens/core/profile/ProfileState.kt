package com.ainshams.graduation_booking_halls.presentation.screens.core.profile

import com.ainshams.graduation_booking_halls.data.remote.dto.DocDataDto
import com.ainshams.graduation_booking_halls.data.remote.dto.ScheduleDto
import com.ainshams.graduation_booking_halls.data.remote.dto.ScheduleDtoItem

data class ProfileState(
    val schedule: ScheduleDto? = null,
    val scheduleDay: List<ScheduleDtoItem>? = null,
    val docData: DocDataDto? = null,
    var isLoading: Boolean = true,
    var networkError: Boolean = false,
    var notFound: Boolean = false,
)