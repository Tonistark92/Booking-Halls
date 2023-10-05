package com.ainshams.graduation_booking_halls.presentation.screens.homewidget


import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import com.ainshams.graduation_booking_halls.presentation.screens.homewidget.MyWidget.Companion.NAME_DEFAULT_VALUE
import com.ainshams.graduation_booking_halls.presentation.screens.homewidget.MyWidget.Companion.STATE_DEFAULT_VALUE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyActionCallback() : ActionCallback {

    /*
    this is a suspend function, you can call a coroutine and an API
     */
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {

        // get updated name ..
//        val currentName = parameters[MyWidget.GLANCE_PARAMETER_KEY] ?: NAME_DEFAULT_VALUE
//        val newName = getNewName(currentName)

        // get updated count ..
//        val currentCount = parameters[MyWidget.GLANCE_COUNT_KEY] ?: DEFAULT_COUNT_VALUE
//        val newCount = getNewCount(currentCount)

        // get updated count ..
        val currentState = parameters[MyWidget.GLANCE_STATE_KEY] ?:STATE_DEFAULT_VALUE
        val newState = getLastState()
        val currentStart = parameters[MyWidget.GLANCE_STATE_KEY] ?:STATE_DEFAULT_VALUE
        val newStart = getLastStart()
        val currentEnd = parameters[MyWidget.GLANCE_STATE_KEY] ?:STATE_DEFAULT_VALUE
        val newEnd = getLastEnd()
        val currentDate = parameters[MyWidget.GLANCE_STATE_KEY] ?:STATE_DEFAULT_VALUE
        val newDate = getLastDate()
        val currentReason = parameters[MyWidget.GLANCE_STATE_KEY] ?:STATE_DEFAULT_VALUE
        val newReason = getLastReason()
        // update the glance data store ..
        updateAppWidgetState(
            context,
            glanceId,
            updateState = { glancePreferences ->
//                glancePreferences[MyWidget.DATA_STORE_NAME_KEY] = newName
//                glancePreferences[MyWidget.DATA_STORE_COUNT_KEY] = newCount
                glancePreferences[MyWidget.DATA_REQUEST_KEY] = newState
                glancePreferences[MyWidget.DATA_START_KEY] = newStart
                glancePreferences[MyWidget.DATA_END_KEY] = newEnd
                glancePreferences[MyWidget.DATA_DATE_KEY] = newDate
                glancePreferences[MyWidget.DATA_REASON_KEY] = newReason
            }
        )

        // recompose widget ..
        MyWidget().update(context, glanceId)
    }

    // test delay to simulate API call ..
//    private suspend fun getNewCount(currentCount: Int): Int {
//        return withContext(Dispatchers.IO) {
////            delay(3000)
//            currentCount + 1
//        }
//    }
    private suspend fun getLastState(): Int {
        return withContext(Dispatchers.IO) {
            val apiService = Retrofit.Builder()
                .baseUrl(ApiService.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        val state= apiService.getAllMyRequests(empId = 29).last().respond_state
            state
        }
    }
    private suspend fun getLastStart(): String {
        return withContext(Dispatchers.IO) {
            val apiService = Retrofit.Builder()
                .baseUrl(ApiService.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        val state= apiService.getAllMyRequests(empId = 29).last().start_time_booking
            state
        }
    }
    private suspend fun getLastEnd(): String {
        return withContext(Dispatchers.IO) {
            val apiService = Retrofit.Builder()
                .baseUrl(ApiService.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
            val state= apiService.getAllMyRequests(empId = 29).last().end_time_booking
            state
        }
    }
    private suspend fun getLastDate(): String {
        return withContext(Dispatchers.IO) {
            val apiService = Retrofit.Builder()
                .baseUrl(ApiService.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
            val state= apiService.getAllMyRequests(empId = 29).last().date_time_send
            state
        }
    }
    private suspend fun getLastReason(): String {
        return withContext(Dispatchers.IO) {
            val apiService = Retrofit.Builder()
                .baseUrl(ApiService.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
            val state= apiService.getAllMyRequests(empId = 29).last().text
            state
        }
    }
//    private fun getNewName(currentName: String): String {
//        return if (currentName == NAME_DEFAULT_VALUE) {
//            namesList.random()
//        } else {
//            val newList = namesList.toMutableList()
//            newList.remove(currentName)
//            newList.random()
//        }
//    }

}
val namesList = listOf(
    "Ahmed",
    "Islam",
    "Abou trika",
    "Mohammed Salah"
)