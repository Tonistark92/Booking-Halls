package com.ainshams.graduation_booking_halls.di

import android.content.Context
import android.net.Uri
import com.ainshams.graduation_booking_halls.data.local.DataStoreRepository
import com.ainshams.graduation_booking_halls.data.remote.HallApi
import com.ainshams.graduation_booking_halls.data.remote.dto.SignUpResponse
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Provides
    @Singleton
    fun provideStockApi(): HallApi {
        val gson = GsonBuilder()
//            .registerTypeAdapter(SignUpResponse::class.java, SignUpResponseDeserializer())
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(HallApi.BaseURL)
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create()
    }
}