package com.ainshams.graduation_booking_halls.utils

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome_screen")
}
