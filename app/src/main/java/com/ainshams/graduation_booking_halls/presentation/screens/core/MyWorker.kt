package com.ainshams.graduation_booking_halls.presentation.screens.core

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.data.remote.dto.NewsResponseItem
import com.ainshams.graduation_booking_halls.presentation.screens.homewidget.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyWorker (var ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params){
    companion object {
         var size = 1
    }
    val apiService = Retrofit.Builder()
        .baseUrl(ApiService.BaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        var temp =apiService.getNews(29)
        Log.d("WORK","HELLO +${temp.size}")
        if (size < temp.size){
            createNotification (ctx,temp.last())
            size = temp.size
        }
        return Result.retry()
    }
@SuppressLint("MissingPermission")
private  fun createNotification (context: Context, item :NewsResponseItem){
    val notificationManager = NotificationManagerCompat.from(context)

    // Create a notification channel (if needed)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "channelId"
        val channelName = "Channel Name"
        val channelDescription = "Channel Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = channelDescription
        }

        notificationManager.createNotificationChannel(channel)
    }

    // Create the notification
    val notification = NotificationCompat.Builder(context, "channelId")
        .setContentTitle(if(item.respond_state> 0){"Be Happy Request Accepted 🥳"}else{"Sadly Request Rejected 🫤"})
        .setContentText("Check Last Request")
        .setSmallIcon(R.drawable.logo)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
        .build()

    // Show the notification
    notificationManager.notify(1, notification)
}
}