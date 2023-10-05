package com.ainshams.graduation_booking_halls.utils

import androidx.annotation.DrawableRes
import com.ainshams.graduation_booking_halls.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.first,
        title = "Halls inspection",
        description = "See All Halls with good management"
    )

    object Second : OnBoardingPage(
        image = R.drawable.second,
        title = "In No Time !",
        description = "lets make the booking more fast and easy "
    )

    object Third : OnBoardingPage(
        image = R.drawable.third,
        title = "Good Communication !",
        description = "You Will Get Notified with Your Booking Process  "
    )
}
