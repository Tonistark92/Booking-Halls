package com.ainshams.graduation_booking_halls.presentation.screens.core.profile.schedule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainshams.graduation_booking_halls.data.remote.dto.ScheduleDtoItem
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModle @Inject constructor(private val repository: HallRepository) : ViewModel() {
    var state by mutableStateOf(ScheduleState())

    init {
        viewModelScope.launch {
            repository.getSchedule(empId = 29).collect { result ->

                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
//                        if (result.data.) {
//                            state.notFound = true
//                        } else {
                        state.notFound = false
                        state.networkError = false
                        result.data.let { schedule ->
                            state = state.copy(
                                schedule = schedule
                            )
                        }
//                        }

                    }

                    is Resource.Error -> state.networkError = true
                }
            }

        }
    }


}

fun ScheduleDtoItem.toEvent(): Event {
//    val datetimeString = "2023-04-22T13:00:00"
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val localDateTimeStart = LocalDateTime.parse(start_time, formatter)
    val localDateTimeEnd = LocalDateTime.parse(end_time, formatter)
    return Event(
        course = course_name,
        color = if (color == "green") {
            Color(android.graphics.Color.parseColor("#67FF68"))
        } else if (color == "red") {
            Color(android.graphics.Color.parseColor("#67FF68"))
        } else {
            Color(android.graphics.Color.parseColor("#9896FF"))

        },
        start = localDateTimeStart,
        end = localDateTimeEnd,
        hall = hall_name
    )
}

