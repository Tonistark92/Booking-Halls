package com.ainshams.graduation_booking_halls.navigation.graphs

import androidx.fragment.app.FragmentActivity
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.ainshams.graduation_booking_halls.presentation.screens.core.requests.RequestsScreen
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.SignUp.SignUpScreen
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.SplashScreen
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.login.LogInScreen
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.onscreens.OnScreen1
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.onscreens.OnScreen2
import com.ainshams.graduation_booking_halls.presentation.screens.wellcom.onscreens.OnScreen3

fun NavGraphBuilder.OnBoardingNavGraph(navController: NavHostController,activity: FragmentActivity) {

    navigation(
        route = Graph.ONBOARDING,
        startDestination = WelcomeScreens.Splash.route,

        ) {
        composable(
            route = WelcomeScreens.Splash.route,
        ) {
            SplashScreen(navController)

        }
        composable(
            route = WelcomeScreens.LogIn.route,
        )
        {
            LogInScreen(navController,activity)

        }
        composable(
            route = WelcomeScreens.SignUp.route,
        )
        {
            SignUpScreen(navController)
        }
                composable(
            route = WelcomeScreens.OnBoarding1.route,)
        {
        OnScreen1(navController)
        }
                composable(
            route =  WelcomeScreens.OnBoarding2.route,)
        {
            OnScreen2(navController)
        }
                composable(
            route =  WelcomeScreens.OnBoarding3.route,)
        {
            OnScreen3(navController)
        }
    }
}

sealed class WelcomeScreens(val route: String) {
    object OnBoarding1 : WelcomeScreens(route = "ONBOARDING1")
    object OnBoarding2 : WelcomeScreens(route = "ONBOARDING2")
    object OnBoarding3 : WelcomeScreens(route = "ONBOARDING3")
    object Splash : WelcomeScreens(route = "SPLASH")
    object LogIn : WelcomeScreens(route = "LOGIN")
    object SignUp : WelcomeScreens(route = "SIGNUP")
}