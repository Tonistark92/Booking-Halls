package com.ainshams.graduation_booking_halls.presentation.screens.core.booking

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ainshams.graduation_booking_halls.data.remote.dto.CourseDtoItem
import com.ainshams.graduation_booking_halls.data.remote.dto.HallParse
import com.ainshams.graduation_booking_halls.data.remote.dto.RequestResponse

data class BookState(
    var hall: HallParse?= null ,
    var courses :List<CourseDtoItem> ?= emptyList(),
    var course :CourseDtoItem ?= null,
    var response: RequestResponse?= null ,
    var isLoading: Boolean = true,
    var networkError: Boolean = false,
    var notFound: Boolean = false,
    var isRequested:Boolean =false,
    var textRequest: MutableState<String> = mutableStateOf("For Argument Purpose")

)
