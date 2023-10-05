package com.ainshams.graduation_booking_halls.presentation.screens.core.home

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainshams.graduation_booking_halls.data.remote.SendAuth
import com.ainshams.graduation_booking_halls.data.remote.SendComplain
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModle @Inject constructor(private val repository: HallRepository) : ViewModel() {

    var state by mutableStateOf(HomeState())

    private var searchJob: Job? = null

    init {
        getHalls()
//        viewModelScope.launch {
//            repository.getNews(empId = 29).collect {}
//            val c = SendComplain("29", "25", "islam islam islam islam")
//            val s = repository.pushComplain(c).collect { result ->
//                when (result) {
//                    is Resource.Loading -> {
//                        state = state.copy(isLoading = result.isLoading)
//                    }
//
//                    is Resource.Success -> {
//                        Log.i("COMPLAIN", result.data.toString())
//                    }
//
//                    is Resource.Error -> state.networkError = true
//
//                }
//            }
//        }

    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSearchQueryChange -> {
                searchHalls(event.query)
            }

            is HomeEvent.Refresh -> {}
        }
    }

    fun searchHalls(query: String) {
        var s: MutableState<String> = mutableStateOf("")
        s.value = query
        state = state.copy(searchQuery = s)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getHalls()
        }
    }
    private fun getHalls(
        query: String = state.searchQuery.value.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            try {
                repository.getHalls(query = query).collect { result ->
//                Log.i("MyTag", result.data.toString())
                    when (result) {
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }

                        is Resource.Success -> {
                            if (result.data?.isEmpty() == true) {
                                state.notFound = true
                            } else {
                                state.notFound = false
                                state.networkError = false
                                result.data?.let { halls ->
                                    state = state.copy(
                                        halls = halls
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
            }

        }
    }


}