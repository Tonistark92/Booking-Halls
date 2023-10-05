package com.ainshams.graduation_booking_halls.presentation.screens.core

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ainshams.graduation_booking_halls.navigation.graphs.RootNavigationGraph
import com.ainshams.graduation_booking_halls.ui.theme.HelpTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class CoreActivity : FragmentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val navController = rememberNavController()
                Scaffold {
                    RootNavigationGraph(navController = rememberNavController(), this@CoreActivity)
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    HelpTheme {
        content()
    }

}

