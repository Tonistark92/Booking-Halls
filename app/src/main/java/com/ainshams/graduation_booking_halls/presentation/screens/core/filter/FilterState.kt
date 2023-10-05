package com.ainshams.graduation_booking_halls.presentation.screens.core.filter

import android.os.Build
import androidx.annotation.RequiresApi
import com.ainshams.graduation_booking_halls.data.remote.dto.FilterDto
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
data class FilterState(
    val halls: MutableList<FilterDto> = mutableListOf(),
    val isLoading: Boolean = false,
//    val showDialog: Boolean = false,
    val isRefreshing: Boolean = false,
    var firstNavigation: Boolean = false,
    var networkError: Boolean = false,
    var notFound: Boolean = false,
    var  date: LocalDate = LocalDate.now(),
    var startTime: LocalTime = LocalTime.NOON,
    var  endTime: LocalTime= LocalTime.NOON,
    var  hallType: List<String> = emptyList(),
    var capasity:Int=0
    )
