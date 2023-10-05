package com.ainshams.graduation_booking_halls.data.remote

import com.ainshams.graduation_booking_halls.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.time.LocalDate
import java.time.LocalTime

interface HallApi {

    companion object {
        //https://booking-apis.larasci.site/filter/:day_date/:start_time/:end_time/:hall_type/:capacity_hall
        const val BaseURL = "https://booking-apis.larasci.site/"
    }

    @GET("api/hall")
    suspend fun getAllHalls(): List<HallsDtoItem>

    @GET("api/hall/{id}")
    suspend fun getHall(
        @Path("id") id: Int
    ): HallParse

    @GET("filter/{day_date}/{start_time}/{end_time}/{hall_type}/{capacity_hall}")
    suspend fun applyFilter(
        @Path("day_date") date: LocalDate,
        @Path("start_time") startTime: LocalTime,
        @Path("end_time") endTime: LocalTime,
        @Path("hall_type") hallType: String,
        @Path("capacity_hall") capasity: Int,
    ): List<FilterDto>

    @GET("api/requst/{employee_num_id}")
    suspend fun getAllMyRequests(
        @Path("employee_num_id") empId: Int
    ): List<RequestsDto>

    @POST("api/requst")
    suspend fun pushRequest(
        @Body request: SendRequest
    ): Response<RequestResponse>

    @POST("api/employee")
    suspend fun pushSignUp(
        @Body credentials: SendSignUp
    ): Response<SignUpResponse>

    @POST("api/complain")
    suspend fun pushComplain(
        @Body complain: SendComplain
    ): Response<SendComplain>

    @GET("api/login/{email}/{password}")
    suspend fun Auth(
        @Path("email") email: String,
        @Path("password") password: String
    ): Int

    @GET("api/get_doctor_courses/{employee_num_id}")
    suspend fun getDocCourses(@Path("employee_num_id") empId: Int): List<CourseDtoItem>

    @GET("api/get_schedule/{employee_num_id}")
    suspend fun getSchedule(@Path("employee_num_id") empId: Int): List<ScheduleDtoItem>

    @GET("api/employee/{id}")
    suspend fun getDocData(@Path("id") empId: Int): DocDataDto

    @GET("api/getRsponds/{employee_num_id}")
    suspend fun getNews(@Path("employee_num_id") id: Int): List<NewsResponseItem>

    @DELETE("api/requst/{id}")
    suspend fun DeleteRequest(@Path("id") reqId: Int): Response<Unit>
}