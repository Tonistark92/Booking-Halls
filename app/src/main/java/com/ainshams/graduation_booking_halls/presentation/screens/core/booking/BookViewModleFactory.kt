package com.ainshams.graduation_booking_halls.presentation.screens.core.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class BookViewModleFactory @Inject constructor(private val repository: HallRepository, private val myInteger: Int) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//        if (modelClass.isAssignableFrom(BookingViewModle::class.java)) {
//            return BookingViewModle(repository,myInteger) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}