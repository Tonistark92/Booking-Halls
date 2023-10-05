package com.ainshams.graduation_booking_halls.presentation.screens.core.filter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainshams.graduation_booking_halls.data.remote.dto.FilterDto
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.presentation.screens.core.home.HomeState
import com.ainshams.graduation_booking_halls.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class FilterViewModel @Inject constructor(private val repository: HallRepository) : ViewModel() {
    var state by mutableStateOf(FilterState())
    fun OnEvent(event: FilterEvent) {
        when (event) {
            is FilterEvent.OnSubmitFilter -> {
                submitFilter(
                    date = state.date,
                    startTime = state.startTime,
                    endTime = state.endTime,
                    hallType = state.hallType,
                    capasity = state.capasity
                )
            }
        }
    }

    fun submitFilter(
        date: LocalDate,
        startTime: LocalTime,
        endTime: LocalTime,
        hallType: List<String>,
        capasity: Int
    ) {
        var listResult: MutableList<FilterDto> = mutableListOf()

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            state.hallType.forEach { hallType ->
                val list = async {
                    repository.getFilteredHalls(
                        date = date,
                        startTime = startTime,
                        endTime = endTime,
                        hallType = hallType,
                        capasity = capasity
                    )
                }
                Log.i("MyTag each list",list.await().toString())
                list.await().forEach {
                    listResult.add(it)
                }
            }
            Log.i("MyTag almost show in ui",listResult.toString())
            listResult.let { halls ->
                Log.i("MyTag almost show in ui",halls.toString())
                state = state.copy(
                    halls = halls
                )
                state = state.copy(isLoading = false)
            }



        }
    }

}
//repository.getFilteredHalls(
//date = date,
//startTime = startTime,
//endTime = endTime,
//hallType = hallType,
//capasity=capasity
//).collect {result->
//    when (result) {
//        is Resource.Loading -> {
//            state = state.copy(isLoading = result.isLoading)
//        }
//        is Resource.Success -> {
//            if(result.data?.isEmpty() == true){
//                state.notFound=true
//            }
//            else{
//                state.notFound=false
//                state.networkError=false
//                result.data?.let { halls ->
//                    Log.i("MyTag",halls.toString())
//                    state = state.copy(
//                        halls = halls
//                    )
//                }
//            }
//
//        }
//        is Resource.Error -> state.networkError=true
//
//    }
//}