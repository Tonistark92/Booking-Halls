package com.ainshams.graduation_booking_halls.presentation.screens.wellcom.onscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ainshams.graduation_booking_halls.navigation.graphs.WelcomeScreens


@Composable
fun OnScreen1(nav: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { nav.navigate(WelcomeScreens.OnBoarding2.route)}) {
            Text(text = "Click me to 2")
        }
    }
}