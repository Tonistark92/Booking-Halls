package com.ainshams.graduation_booking_halls.di

//import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
//import com.ainshams.graduation_booking_halls.presentation.screens.core.details.DetailsViewModle
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//import javax.inject.Named
//
//@Module
//@InstallIn(ActivityComponent::class)
//object MyModule {
//    @Provides
//    fun provideMyViewModelFactory(repository: HallRepository, @Named("id") id: String): DetailsViewModle {
//        return DetailsViewModle(repository, id)
//    }
//
//    @Provides
//    @Named("id")
//    fun provideMyString(@Named("id") id: String): String {
//        return id // Use the dynamic string value here
//    }
//
////    @Provides
////    @Named("myId")
////    fun provideMyId(): String {
////        return "world" // Replace with your dynamic string value
////    }
//}