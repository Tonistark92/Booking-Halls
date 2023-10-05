package com.ainshams.graduation_booking_halls.presentation.screens.core.profile

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
class ProfileViewModle @Inject constructor(private val repository: HallRepository) : ViewModel() {
    var state by mutableStateOf(ProfileState())
    var selected =mutableStateOf("")

    init {
        viewModelScope.launch {
            try {
                repository.getDocData(empId = 29).collect{ result->

                    when (result) {
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                        if (state.schedule?.isEmpty() == true) {
                            state.isLoading=false
                            state.notFound = true
                        } else {
                            state.notFound = false
                            state.networkError = false
                            result.data.let { docData ->
                                state = state.copy(
                                    docData = docData
                                )
                            }
                        }

                        }
                        is Resource.Error -> state.networkError = true
                    }
                }

            }
            catch (e:Exception){
                state.networkError = true
                state.notFound = true
            }

        }


    }

}