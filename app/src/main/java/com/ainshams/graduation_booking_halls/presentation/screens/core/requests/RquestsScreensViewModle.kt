package com.ainshams.graduation_booking_halls.presentation.screens.core.requests

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RquestsScreensViewModle @Inject constructor(private val repository: HallRepository) :
    ViewModel() {
    var state by mutableStateOf(RequestsStat())
    init {
        getAllMyRquests()
    }
    private fun getAllMyRquests(id:Int=29){
        viewModelScope.launch {
        repository.getAllMyRequests(empId = 29).collect{result ->
            when (result) {
                is Resource.Loading -> {
                    state = state.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    if(result.data?.isEmpty() == true){
                        state.notFound=true
                    }
                    else{
                        state.notFound=false
                        state.networkError=false
                        result.data?.let { requests ->
                            state = state.copy(
                                Requests = requests.reversed()
                            )
                        }

                    }

                }
                is Resource.Error -> state.networkError=true

            }

        }


        }
        }
    fun refreshing(){
        viewModelScope.launch {
            state.isLoading =true
            repository.getAllMyRequests(empId = 29).collect{result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        if(result.data?.isEmpty() == true){
                            state.notFound=true
                        }
                        else{
                            state.notFound=false
                            state.networkError=false
                            result.data?.let { requests ->
                                state = state.copy(
                                    Requests = requests.reversed()
                                )
                            }

                        }

                    }
                    is Resource.Error -> state.networkError=true

                }

            }


        }
    }
    fun deleteReq(id:Int){
        viewModelScope.launch {
            state.Requests.filter { it.employee_num_id !=id }
            repository.DeleteRequest(id)
        }
    }
}