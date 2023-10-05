package com.ainshams.graduation_booking_halls.navigation
//the screens
enum class HallsScreans {
    HomeScrean,
    ProfileScreen,
    BookingScreen,
    RequestScreen;
    companion object {
        fun fromRoute(route:String?): HallsScreans = when(route?.substringBefore("/")){
            HomeScrean.name->HomeScrean
            ProfileScreen.name->ProfileScreen
            BookingScreen.name->BookingScreen
            null->HomeScrean
            else->throw IllegalArgumentException("Route $route is not knowen")
        }
    }
}