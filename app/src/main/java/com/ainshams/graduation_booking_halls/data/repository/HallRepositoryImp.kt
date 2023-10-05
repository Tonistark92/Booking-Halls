package com.ainshams.graduation_booking_halls.data.repository

import android.util.Log
import com.ainshams.graduation_booking_halls.data.remote.HallApi
import com.ainshams.graduation_booking_halls.data.remote.SendComplain
import com.ainshams.graduation_booking_halls.data.remote.SendRequest
import com.ainshams.graduation_booking_halls.data.remote.SendSignUp
import com.ainshams.graduation_booking_halls.data.remote.dto.*
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HallRepositoryImp @Inject constructor(
    private val api: HallApi,
) : HallRepository {
    override suspend fun getHalls(
        query: String
    ): Flow<Resource<List<HallsDtoItem>>> =
        flow {
            emit(Resource.Loading(true))

            var list: List<HallsDtoItem> = emptyList()
            try {
                list = api.getAllHalls()
                emit(Resource.Loading(false))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
            if (query == "") {
                emit(Resource.Success(data = list))
                emit(Resource.Loading(false))
            } else {
                list = list.filter { it.hall_name.lowercase().contains(query) }
                emit(Resource.Success(data = list))
                emit(Resource.Loading(false))
            }
        }

    override suspend fun getHall(id: Int): Flow<Resource<HallParse>> =
        flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(data = api.getHall(id = id)))
            emit(Resource.Loading(false))
        }

    override suspend fun getFilteredHalls(
        date: LocalDate,
        startTime: LocalTime,
        endTime: LocalTime,
        hallType: String,
        capasity: Int
    ): List<FilterDto> {
        var list: List<FilterDto> = emptyList()
        try {
            list = api.applyFilter(
                date = date,
                startTime = startTime,
                endTime = endTime,
                hallType = hallType,
                capasity = capasity
            )
//            Log.i("MyTag api ",list.toString())

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: HttpException) {
            e.printStackTrace()
        }
        return list
    }


    override suspend fun getAllMyRequests(empId: Int): Flow<Resource<List<RequestsDto>>> = flow {
        emit(Resource.Loading(true))
        var list: List<RequestsDto> = emptyList()
        try {

            list = api.getAllMyRequests(empId = empId)
            emit(Resource.Success(data = list))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Find data for this date & time"))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
        }
        emit(Resource.Loading(false))
    }

    override suspend fun getAllDocCourses(empId: Int): Flow<Resource<List<CourseDtoItem>>> = flow {
        emit(Resource.Loading(true))

        emit(
            Resource.Success(
                data = api.getDocCourses(
                    empId = 29
                )
            )
        )
        emit(Resource.Loading(false))
    }

    override suspend fun getSchedule(empId: Int): Flow<Resource<List<ScheduleDtoItem>>> = flow {
        emit(Resource.Loading(true))
        Log.d(
            "DATA", api.getSchedule(
                empId = 29
            ).toString()
        )
        emit(
            Resource.Success(
                data = api.getSchedule(
                    empId = 29
                )
            )
        )

        emit(Resource.Loading(false))
    }

    override suspend fun getDocData(empId: Int): Flow<Resource<DocDataDto>> = flow {
        emit(Resource.Loading(true))

        emit(
            Resource.Success(
                data = api.getDocData(
                    empId = 29
                )
            )
        )
        emit(Resource.Loading(false))
    }

    override suspend fun getAuth(email: String, password: String): Flow<Resource<Int>> = flow {
        emit(Resource.Loading(true))

        emit(
            Resource.Success(
                data = api.Auth(
                    email = email,
                    password = password
                )
            )
        )
        Log.i(
            "AUTH", api.Auth(
                email = email,
                password = password
            ).toString()
        )
        emit(Resource.Loading(false))
    }

    override suspend fun pushRequest(
        request: SendRequest
    ): Flow<Resource<Response<RequestResponse>>> =
        flow {
            emit(Resource.Loading(true))
            emit(
                Resource.Success(
                    data = api.pushRequest(
                        request = request
                    )
                )
            )
            emit(Resource.Loading(false))
        }

    override suspend fun pushSignUp(credentials: SendSignUp): Flow<Resource<Response<SignUpResponse>>> =
        flow {
            emit(Resource.Loading(true))
            emit(
                Resource.Success(
                    data = api.pushSignUp(
                        credentials = credentials
                    )
                )
            )
//            Log.(
//                "SIGN", api.pushSignUp(
//                    credentials = credentials
//                ).toString()
//            )
            emit(Resource.Loading(false))
        }

    override suspend fun pushComplain(complain: SendComplain): Flow<Resource<Response<SendComplain>>> =
        flow {
            emit(Resource.Loading(true))
            emit(
                Resource.Success(
                    data = api.pushComplain(
                        complain = complain
                    )
                )
            )
            Log.i(
                "COMP", api.pushComplain(
                    complain = complain
                ).toString()
            )
            emit(Resource.Loading(false))
        }


    override suspend fun getNews(empId: Int): Flow<Resource<List<NewsResponseItem>>> = flow {
        emit(Resource.Loading(true))
        emit(
            Resource.Success(
                data = api.getNews(
                    id = empId
                )
            )
        )
//        Log.i("Data", api.getNews(id = empId).toString())
        emit(Resource.Loading(false))

    }

    override suspend fun DeleteRequest(id: Int): Response<Unit> {
//        Log.i(
//            "DELETE", api.DeleteRequest(
//                reqId = id
//            ).toString()
//        )
        return api.DeleteRequest(
            reqId = id
        )
    }
}
