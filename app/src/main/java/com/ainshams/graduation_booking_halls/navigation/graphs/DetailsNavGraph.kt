package com.ainshams.graduation_booking_halls.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ainshams.graduation_booking_halls.presentation.screens.core.booking.BookScreen
import com.ainshams.graduation_booking_halls.presentation.screens.core.details.NewDetailsScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS + "/{id}",
        startDestination = DetailsScreen.Information.route + "/{id}",

        ) {
        composable(
            route = DetailsScreen.Information.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
//            val parentEntry = remember(it) { navController.getBackStackEntry(DetailsScreen.Information.route+"/{id}") }
//            val id = parentEntry.arguments?.getString("id")
            val id = requireNotNull(it.arguments).getString("id")
            NewDetailsScreen(id = id!!.toInt())

        }
        composable(
            route = DetailsScreen.CheckOut.route + "/{id}" + "/{start}" + "/{end}" + "/{date}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("start") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("end") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("date") {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
            val id = requireNotNull(it.arguments).getString("id")
            val start = requireNotNull(it.arguments).getString("start")
            val end = requireNotNull(it.arguments).getString("end")
            val date = requireNotNull(it.arguments).getString("date")
            BookScreen(id = id!!.toInt(), start = start!!,end=end!!,date=date!!,navController)
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object CheckOut : DetailsScreen(route = "CHECKOUT")
    object Schedule : DetailsScreen(route = "SCHEDULE")
}
