package com.ainshams.graduation_booking_halls.presentation.screens.wellcom.SignUp

import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.ainshams.graduation_booking_halls.data.remote.SendSignUp
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.login.LogInState
import com.ainshams.graduation_booking_halls.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SignUpViewModle @Inject constructor(
    private val repository: HallRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(SignUpState())

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val snackbarHostState = SnackbarHostState()

    fun pushSignUp() {
        viewModelScope.launch {
            repository.pushSignUp(
                request = SendSignUp(
                    state.name.value,
                    state.email.value,
                    state.password.value,
                    state.phone.value,
                    state.specialization.value
                )
            ).collect { result ->
                when (result) {

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
                        result.data?.body().let {
                            state = state.copy(response = result.data?.body())
                        }
                        snackbarHostState.showSnackbar(
                            " Signed Up successfully ",
                            duration = SnackbarDuration.Short
                        )
                        sharedPreferences.edit()
                            .putString("id", result.data?.body()?.employee_id.toString())
                            .apply()
                        sharedPreferences.edit()
                            .putString("name", result.data?.body()?.employee_name)
                            .apply()
                        sharedPreferences.edit().putString("email", result.data?.body()?.email)
                            .apply()
                    }

                    is Resource.Error -> {
                        state.networkError = true
                        snackbarHostState.showSnackbar(
                            "Couldn't Signed Up successfully ",
                            duration = SnackbarDuration.Short
                        )
                    }

                }
            }
        }
    }
}