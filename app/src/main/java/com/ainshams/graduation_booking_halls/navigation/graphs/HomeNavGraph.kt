package com.ainshams.graduation_booking_halls.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.FilterScreen
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.FilterScreenWithAction
import com.ainshams.graduation_booking_halls.presentation.screens.core.home.Home
import com.ainshams.graduation_booking_halls.presentation.screens.core.profile.ProfileScreen
import com.ainshams.graduation_booking_halls.presentation.screens.core.profile.schedule.ScheduleScreen
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(navController: NavHostController,nav: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HallsScreans.HomeScrean.name
    ) {
        composable(route = HallsScreans.HomeScrean.name) {
           Home(navController) {

               navController.navigate(DetailsScreen.Information.route+"/".plus(it.toString()))
           }
        }
        composable(route =  HallsScreans.BookingScreen.name) {
            FilterScreen(navController)
        }

        composable(route =  "filter") {
            FilterScreenWithAction(){ id:Int, start:LocalTime, end: LocalTime, date: LocalDate ->
                navController.navigate(DetailsScreen.CheckOut.route+"/".plus(id.toString())+"/".plus(start.toString())+"/".plus(end.toString())+"/".plus(date.toString()))
            }
        }

        composable(route =  HallsScreans.ProfileScreen.name) {
            ProfileScreen(nav ,navController)
        }
        composable(route =  DetailsScreen.Schedule.route) {
            ScheduleScreen()
        }

        detailsNavGraph(navController = navController)
        RequestsNavGraph(navController = navController)

    }
}

