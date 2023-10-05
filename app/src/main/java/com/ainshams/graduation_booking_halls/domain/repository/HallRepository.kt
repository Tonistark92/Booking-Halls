package com.ainshams.graduation_booking_halls.domain.repository

import com.ainshams.graduation_booking_halls.data.remote.SendComplain
import com.ainshams.graduation_booking_halls.data.remote.SendRequest
import com.ainshams.graduation_booking_halls.data.remote.SendSignUp
import com.ainshams.graduation_booking_halls.data.remote.dto.*
import com.ainshams.graduation_booking_halls.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalTime

interface HallRepository {
    suspend fun getHalls(
        query: String
    ): Flow<Resource<List<HallsDtoItem>>>

    suspend fun getHall(id: Int): Flow<Resource<HallParse>>

    suspend fun getFilteredHalls(
        date: LocalDate,
        startTime: LocalTime,
        endTime: LocalTime,
        hallType: String,
        capasity: Int
    ): List<FilterDto>

    suspend fun pushRequest(request: SendRequest): Flow<Resource<Response<RequestResponse>>>

    suspend fun pushSignUp(request: SendSignUp): Flow<Resource<Response<SignUpResponse>>>

    suspend fun pushComplain(complain: SendComplain): Flow<Resource<Response<SendComplain>>>

    suspend fun getAuth(email: String, password: String): Flow<Resource<Int>>

    suspend fun getAllMyRequests(empId: Int): Flow<Resource<List<RequestsDto>>>

    suspend fun getAllDocCourses(empId: Int): Flow<Resource<List<CourseDtoItem>>>

    suspend fun getSchedule(empId: Int): Flow<Resource<List<ScheduleDtoItem>>>

    suspend fun getDocData(empId: Int): Flow<Resource<DocDataDto>>

    suspend fun getNews(empId: Int): Flow<Resource<List<NewsResponseItem>>>

    suspend fun DeleteRequest(id:Int): Response<Unit>

}