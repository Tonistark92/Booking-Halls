package com.ainshams.graduation_booking_halls.presentation.screens.core.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.data.remote.dto.HallsDtoItem
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.ainshams.graduation_booking_halls.navigation.graphs.HomeNavGraph
import com.ainshams.graduation_booking_halls.presentation.screens.components.NavBar
import com.ainshams.graduation_booking_halls.presentation.screens.components.list
import com.google.accompanist.flowlayout.FlowRow

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewHomeScreen(
    navController: NavHostController
) {
    var navHome :NavHostController = rememberNavController()
  Scaffold (
       bottomBar = {    NavBar(list, navController = navHome) }
  ){

     HomeNavGraph(navController = navHome,nav = navController)
  }


}



@Preview(showBackground = true)
@Composable
fun prev() {
//    NewHomeScreen()
}