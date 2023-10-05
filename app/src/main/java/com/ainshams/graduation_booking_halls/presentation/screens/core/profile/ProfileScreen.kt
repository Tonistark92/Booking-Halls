package com.ainshams.graduation_booking_halls.presentation.screens.core.profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.data.remote.dto.CourseDtoItem
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.ainshams.graduation_booking_halls.navigation.graphs.DetailsScreen
import com.ainshams.graduation_booking_halls.navigation.graphs.Graph
import com.ainshams.graduation_booking_halls.navigation.graphs.WelcomeScreens


@Composable
fun ProfileScreen(nav: NavHostController,homeNav:NavHostController) {
    val viewModle: ProfileViewModle = hiltViewModel()
    val state = viewModle.state
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(color = Color(0xff15420F ), strokeWidth = 4.dp)
        } else if (state.networkError || state.notFound) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Network Error ")
                Spacer(modifier = Modifier.height(200.dp))
            }

        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFECECEC))
            ) {
             Column(modifier = Modifier
                 .fillMaxSize()
                 .background(Color(0xFFECECEC))
                 .padding(start = 20.dp, end = 20.dp)) {
                 Row(
                     modifier = Modifier
                         .fillMaxWidth()
                         .height(100.dp)
                         .padding(bottom = 12.dp)
//                         .background(Color.White)
                     ,
                     horizontalArrangement = Arrangement.Center,
                     verticalAlignment = Alignment.CenterVertically
                 ) {
                     Text(
                         text = "Profile",
                         color = Color.Black,
                         fontSize = 30.sp,
                         fontWeight = FontWeight.ExtraBold
                     )
                 }

                 Box(
                     modifier = Modifier
                         .fillMaxWidth()
                         .border(width = 1.dp, color = Color.LightGray),
                     contentAlignment = Alignment.Center
                 ) {
                     Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                         .fillMaxWidth()
                         .padding(start = 10.dp, end = 10.dp)) {
                       if (imageUri ==null){
                           Image(
                               painter = painterResource(id = R.drawable.holder),
                               contentDescription = null,
                               modifier = Modifier
                                   .size(50.dp)
                                   .clip(CircleShape)
                                   .clickable { launcher.launch("image/*") },
                               contentScale = ContentScale.Crop
                           )

                       }
                         else{
                           imageUri?.let {

                               bitmap = if (Build.VERSION.SDK_INT < 28) {
                                   MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                               } else {
                                   val source = ImageDecoder.createSource(context.contentResolver, it)
                                   ImageDecoder.decodeBitmap(source)
                               }

                               Image(
                                   bitmap = bitmap?.asImageBitmap()!!,
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(50.dp)
                                       .clip(CircleShape)
                                       .clickable { launcher.launch("image/*") },
                                   contentScale = ContentScale.Crop
                               )

                           }

                       }

                         Spacer(modifier = Modifier.width(20.dp))
                         Column(
                             horizontalAlignment = Alignment.Start,
                             verticalArrangement = Arrangement.Center
                         ) {

//                             Text(text = state.docData!!.employee_name, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 30.sp)
//                             Text(text = state.docData.email, color = Color.LightGray)
                             Text(text = "Dr/islam_demo", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                             Text(text = "islam@sci.com", color = Color.LightGray)
                         }
                     }
                 }
//                 dropDownMenu {
//                     viewModle.selected.value = it
//                viewModle.showSchedule()
//                 }
//                 LazyRow{
//                     items(viewModle.state.scheduleDay)
//                 }
                 Spacer(modifier = Modifier.size(20.dp))
                 Text(
                     text = buildAnnotatedString {
                         withStyle(
                             style = SpanStyle(
                                 color = Color(
                                     android.graphics.Color.parseColor(
                                         "#E2AC80"
                                     )
                                 ),
                                 textDecoration = TextDecoration.Underline
                             )
                         ) {
                             append("See Whole Week Schedule?")
                         }
                     },
                     modifier = Modifier.clickable { homeNav.navigate(DetailsScreen.Schedule.route) })
                 Spacer(modifier = Modifier.height(400.dp))
                 Button(
                     onClick = { nav.navigate(WelcomeScreens.LogIn.route) },
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(start = 15.dp, end = 15.dp)
                         .height(50.dp),
                     colors = ButtonDefaults.buttonColors(
                         backgroundColor = Color(0xff15420F ), // Set the background color
                         contentColor = Color.White // Set the text color
                     )
                 ) {
                     Text(text = "Log Out")
                 }
             }
            }

        }
    }

}


@Composable
private fun dropDownMenu(onSelected: (day: String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { enterdTest ->
                selectedText = enterdTest
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text("Choose Day", color = Color.LightGray) },
            trailingIcon = {
                Icon(
                    icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded }, tint = Color.LightGray
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black, // Set text color
                backgroundColor = Color.White, // Set background color
                focusedLabelColor = Color.LightGray, // Set focused hint color
                unfocusedLabelColor = Color.LightGray, // Set unfocused hint color
                disabledLabelColor = Color.LightGray, // Set disabled hint color
                cursorColor = Color.Transparent, // Hide the cursor
                focusedBorderColor = Color.Transparent, // Remove border when focused
                unfocusedBorderColor = Color.Transparent, // Remove border when unfocused
                disabledBorderColor = Color.Transparent ,// Remove border when disabled

            ),
            enabled = false

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                .background(Color.White)
        ) {
            suggestions?.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = label
                        onSelected(label)
                        expanded = false
                    },
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(text = label, color = Color.Black)
                }
            }
        }
    }

}