package com.ainshams.graduation_booking_halls.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.ainshams.graduation_booking_halls.presentation.screens.core.requests.RequestsScreen


fun NavGraphBuilder.RequestsNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.REQUESTS,
        startDestination =HallsScreans.RequestScreen.name ,

        ) {
        composable(
            route =HallsScreans.RequestScreen.name,
        ) {
          RequestsScreen {

          }

        }
//        composable(
//            route = RequestScreens.RequestDetails.route,
//        {
//
//        }
    }
}
sealed class RequestScreens(val route: String) {
    object AllRequests : RequestScreens(route = "REQUESTS")
    object RequestDetails : RequestScreens(route = "REQUESTINFO")
}