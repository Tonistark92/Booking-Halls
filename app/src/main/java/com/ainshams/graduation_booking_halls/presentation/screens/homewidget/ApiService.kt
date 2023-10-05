package com.ainshams.graduation_booking_halls.presentation.screens.homewidget

import com.ainshams.graduation_booking_halls.data.remote.dto.HallParse
import com.ainshams.graduation_booking_halls.data.remote.dto.NewsResponseItem
import com.ainshams.graduation_booking_halls.data.remote.dto.RequestsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        //https://booking-apis.larasci.site/filter/:day_date/:start_time/:end_time/:hall_type/:capacity_hall
        const val BaseURL = "https://booking-apis.larasci.site/"
    }
    @GET("api/requst/{employee_num_id}")
    suspend fun getAllMyRequests(
        @Path("employee_num_id") empId: Int
    ): MutableList<RequestsDto>

    @GET("api/getRsponds/{employee_num_id}")
    suspend fun getNews(@Path("employee_num_id") id: Int): List<NewsResponseItem>

    @GET("api/hall/{id}")
    suspend fun getHall(
        @Path("id") id: Int
    ): HallParse
}