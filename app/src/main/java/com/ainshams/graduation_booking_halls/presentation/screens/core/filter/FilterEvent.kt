package com.ainshams.graduation_booking_halls.presentation.screens.core.filter

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
sealed class FilterEvent{
    object OnSubmitFilter :FilterEvent()
}
