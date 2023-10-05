package com.ainshams.graduation_booking_halls.presentation.screens.wellcom

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.window.SplashScreen
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.navigation.graphs.Graph
import com.ainshams.graduation_booking_halls.navigation.graphs.WelcomeScreens
import com.ainshams.graduation_booking_halls.presentation.screens.core.MyWorker
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(nav: NavHostController) {
    val c =LocalContext.current
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c)
    var auth    = remember {
        mutableStateOf(sharedPreferences.getString("key", "") ?: "")
    }
        auth.value = sharedPreferences.getString("auth","").toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val permision = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

        BottomToCenterAnimation() {
            if (permision.status.isGranted){
                val myWork = PeriodicWorkRequestBuilder<MyWorker>(
                    5, TimeUnit.MINUTES
                ).build()
                val workManager = WorkManager.getInstance(c)
                workManager.enqueueUniquePeriodicWork("WORK", ExistingPeriodicWorkPolicy.REPLACE, myWork)
            }else{
                permision.launchPermissionRequest()
            }
            if (auth.value == "AUTH"){
                nav.navigate(WelcomeScreens.LogIn.route)
            }
            else{

            nav.navigate(WelcomeScreens.SignUp.route)
            }
        }

    }
}


@Composable
fun BottomToCenterAnimation(navigateToNextScreen: () -> Unit) {
    val startY = 100.dp
    val endY = 0.dp

    val animatedY = remember { Animatable(startY.value) }

    LaunchedEffect(Unit) {
        animatedY.animateTo(
            endY.value,
            animationSpec = tween(durationMillis = 3000)
        )
        navigateToNextScreen.invoke() // Navigate after the animation completes
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = animatedY.value.dp),
        contentAlignment = Alignment.Center
    ) {
        // Your composable content goes here
        Image(painter = painterResource(R.drawable.splash_icon), contentDescription = "")
    }
}