package com.ainshams.graduation_booking_halls.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ainshams.graduation_booking_halls.presentation.screens.core.CoreActivity
import com.ainshams.graduation_booking_halls.presentation.screens.core.home.NewHomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navController: NavHostController,activity: CoreActivity) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.ONBOARDING
    ) {
//        authNavGraph(navController = navController)
        OnBoardingNavGraph(navController = navController,activity)
        composable(route = Graph.HOME) {
            NewHomeScreen(navController)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val ONBOARDING = "onboarding_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
    const val REQUESTS = "requests_graph"
}