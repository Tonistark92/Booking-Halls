package com.ainshams.graduation_booking_halls.presentation.screens.core.booking

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainshams.graduation_booking_halls.data.remote.SendRequest
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class BookingViewModle @Inject constructor(private val repository: HallRepository) : ViewModel() {
    var state by mutableStateOf(BookState())
    var id = mutableStateOf(-1)
    var start = mutableStateOf("")
    var end = mutableStateOf("")
    var date = mutableStateOf("")
    val bookingNum = mutableStateOf(0)
    var text = mutableStateOf("")

    init {
        viewModelScope.launch {

            repository.getAllDocCourses(empId = 29).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        if (result.data.isNullOrEmpty()) {
                            state.notFound = true
                        } else {
                            state.notFound = false
                            state.networkError = false
                            result.data.let { courses ->
                                state = state.copy(
                                    courses = courses
                                )
                            }
                        }

                    }
                    is Resource.Error -> state.networkError = true
                }
            }

        }
    }

    fun setData(id: Int, start: String, end: String, date: String) {
        this.id.value = id
        this.start.value = start
        this.end.value = end
        this.date.value = date
        getHall(idl = this.id.value)
    }

    fun getHall(idl: Int) {
        viewModelScope.launch {
            repository.getHall(id = idl).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        if (result.data?.hall_data!!.hall_name == "") {
                            state.notFound = true
                        } else {
                            state.notFound = false
                            state.networkError = false
                            result.data.let { halls ->
                                state = state.copy(
                                    hall = halls
                                )
                            }
                        }

                    }
                    is Resource.Error -> state.networkError = true

                }

            }
        }
    }

    fun pushRequest() {
        viewModelScope.launch {
//        val data = hashMapOf<Any,Any>()
//            data["employee_num_id"]=29
//            data["hall_num"]=id.value
//            data["booking_day"]=date.value
//            data["start_time_booking"]=start.value
//            data["end_time_booking"]=end.value
//            data["permental"]=3
//            data["text"]="Hello my frienda"
            val request = SendRequest(
                employee_num_id = "29",
                hall_num = id.value.toString(),
                booking_day = date.value,
                start_time_booking = start.value,
                end_time_booking = end.value,
                permental = bookingNum.value.toString(),
                text = state.textRequest.value,
                code = try {
                    state.course!!.course_code
                }catch (e:NullPointerException){
                    ""
                }
            )
            repository.pushRequest(
                request = request
            ).collect { result ->
//                Log.i("MyTag Booking request", result.data?.message().toString()+ state.response.toString())
                when (result) {

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
//                        Log.i("MyTag Booking request", result.data?.body().toString())
                        result.data?.body().let {
                            state = state.copy(response = result.data?.body())
                        }
                        state = state.copy(isRequested = true)

                    }
                    is Resource.Error -> state.networkError = true

                }
            }

        }
    }

}