package com.ainshams.graduation_booking_halls.presentation.screens.wellcom.SignUp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ainshams.graduation_booking_halls.data.remote.dto.SignUpResponse

data class SignUpState(
    val imageFinished: MutableState<String> = mutableStateOf(""),
    val name: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> = mutableStateOf(""),
    val phone: MutableState<String> = mutableStateOf(""),
    var specialization: MutableState<String> = mutableStateOf(""),
    var response: SignUpResponse?= null,
    var isLoading: Boolean = true,
    var networkError: Boolean = false,
    var notFound: Boolean = false,
)
