package com.ainshams.graduation_booking_halls.presentation.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.ainshams.graduation_booking_halls.navigation.graphs.RequestScreens

@Composable
fun NavBar(
    list: List<NavItem>,
    defaultSelectedIndex: Int = 0,
    navController: NavHostController
) {
    val screens = listOf(
        HallsScreans.HomeScrean,
        HallsScreans.BookingScreen,
        HallsScreans.ProfileScreen,
       HallsScreans.RequestScreen,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.name == currentDestination?.route }
    if (bottomBarDestination) {
        Box(
            Modifier
                .fillMaxWidth(95.0f)
                .height(56.dp)
                .background(Color.White),
        ) {

            var selectedIndex by remember {
                mutableStateOf(defaultSelectedIndex)
            }

            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier
                    .fillMaxSize(),
            ) {

                list.forEachIndexed { index, nav ->

                    Box(
                        Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .clickable {
                                selectedIndex = index
                                if (selectedIndex ==0){navController.navigate(HallsScreans.HomeScrean.name)}
                                else if(selectedIndex ==1){navController.navigate(HallsScreans.BookingScreen.name)}
                                else if(selectedIndex ==2){navController.navigate(HallsScreans.RequestScreen.name)}
                                else if(selectedIndex ==3){ navController.navigate(HallsScreans.ProfileScreen.name)}
                            },
                        contentAlignment = Center
                    ) {

                        Column(
                            if (selectedIndex == index)
                                Modifier.offset(y = (-8).dp)
                            else Modifier,
                            horizontalAlignment = CenterHorizontally,
                        ) {
                            Box(
                                Modifier
                                    .background(
                                        if (selectedIndex == index) Color(0xff15420F )
                                        else Color.Transparent,
                                        CircleShape
                                    )
                                    .size(36.dp),
                                contentAlignment = Center
                            ) {
                                Icon(
                                    painterResource(nav.icon),
                                    null,
                                    Modifier.size(24.dp),
                                    tint = if (selectedIndex != index) Color.Gray else Color.White
                                )
                            }

                            AnimatedVisibility(selectedIndex == index) {
                                Text(
                                    nav.title,
                                    modifier = Modifier
                                        .padding(top = 4.dp),
                                    color = Color(0xff15420F ),
                                    fontSize = 12.sp
                                )
                            }
                        }

                    }

                }

            }

        }
    }

}

data class NavItem(
    @DrawableRes val icon: Int,
    val title: String
)
val list = listOf(
    NavItem(
        R.drawable.home_ic,
        "Home"
    ),
    NavItem(
        R.drawable.book_icon,
        "Booking"
    ),
    NavItem(
        R.drawable.request_icon,
        "Requests"
    ),
    NavItem(
        R.drawable.profile_icon,
        "Profile"
    ),

)
@Preview(showBackground = true)
@Composable
fun Prev() {
//    NavBar(list)
}