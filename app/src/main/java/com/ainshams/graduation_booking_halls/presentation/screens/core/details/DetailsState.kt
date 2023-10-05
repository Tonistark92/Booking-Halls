package com.ainshams.graduation_booking_halls.presentation.screens.core.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ainshams.graduation_booking_halls.data.remote.dto.HallParse

data class DetailsState(
    var hall: HallParse?= null,
    val isLoading: Boolean = true,
    var networkError: Boolean = false,
    var notFound: Boolean = false,
    val textComplian: MutableState<String> = mutableStateOf("")

)
