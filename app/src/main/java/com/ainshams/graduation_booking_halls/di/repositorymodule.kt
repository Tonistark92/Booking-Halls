package com.ainshams.graduation_booking_halls.di

import com.ainshams.graduation_booking_halls.data.repository.HallRepositoryImp
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class repositorymodule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        hallsRepositoryImp: HallRepositoryImp
    ): HallRepository
}