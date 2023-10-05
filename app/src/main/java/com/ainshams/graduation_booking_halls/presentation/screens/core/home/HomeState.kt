package com.ainshams.graduation_booking_halls.presentation.screens.core.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ainshams.graduation_booking_halls.data.remote.dto.HallsDtoItem


data class HomeState (
    val halls: List<HallsDtoItem> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    var networkError: Boolean = false,
    var notFound: Boolean = false,
    val searchQuery: MutableState<String> = mutableStateOf("")
)