package com.ainshams.graduation_booking_halls.presentation.screens.wellcom.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LogInState(
    val emailField: MutableState<String> = mutableStateOf(""),
    val passwordField: MutableState<String> = mutableStateOf(""),
    val isFirst: Boolean=true,
    var isAuth: Boolean=false,
    var empId: Int=0,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    var networkError: Boolean = false,
    var notFound: Boolean = false,


    )
