package com.ainshams.graduation_booking_halls.presentation.screens.core.requests

import com.ainshams.graduation_booking_halls.data.remote.SendRequest
import com.ainshams.graduation_booking_halls.data.remote.dto.RequestsDto

data class RequestsStat(
    val Requests: List<RequestsDto> = emptyList(),
    var isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    var networkError: Boolean = false,
    var notFound: Boolean = false,
)