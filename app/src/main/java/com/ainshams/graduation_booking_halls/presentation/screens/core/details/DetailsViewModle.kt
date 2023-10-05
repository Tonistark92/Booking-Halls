package com.ainshams.graduation_booking_halls.presentation.screens.core.details

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.ainshams.graduation_booking_halls.data.remote.SendComplain
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.utils.Resource
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModle @Inject constructor(
    private val repository: HallRepository,
    @ApplicationContext private val context: Context
) :
    ViewModel() {
    var state by mutableStateOf(DetailsState())
    var id = mutableStateOf(-1)
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//    val myId = sharedPreferences.getInt("id", -1)
    fun setId(id: Int) {
        if (this.id.value == -1) {

            this.id.value = id
            getHall(idl = this.id.value)
        }
    }

    fun getHall(idl: Int) {
        viewModelScope.launch {
            delay(1000)
            repository.getHall(id = idl).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
//                        Log.i("MyTag Details", result.data.toString())
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

    fun pushComplain() {
        viewModelScope.launch {
            val c = SendComplain(
                "29",
                state.hall?.hall_data?.hall_id.toString(),
                state.textComplian.value
            )

            val s = repository.pushComplain(c).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
                        Log.i("COMPLAIN", result.data.toString())
                        state.textComplian.value = ""
                    }

                    is Resource.Error -> state.networkError = true

                }
            }
        }
    }
}
