package com.ainshams.graduation_booking_halls.presentation.screens.core.booking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.glance.appwidget.action.actionStartActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ainshams.graduation_booking_halls.components.InputField
import com.ainshams.graduation_booking_halls.components.RoundIconButton
import com.ainshams.graduation_booking_halls.data.remote.dto.CourseDtoItem
import com.ainshams.graduation_booking_halls.navigation.HallsScreans
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import java.util.*
import kotlin.math.absoluteValue

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookScreen(id: Int, start: String, end: String, date: String,nav :NavHostController) {
    val viewModle: BookingViewModle = hiltViewModel()
    val state = viewModle.state
    val getdata = remember {
        mutableStateOf(false)
    }
    val isPermenent = remember {
        mutableStateOf(false)
    }
    if (getdata.value == false) {

        viewModle.setData(id = id, start = start, end = end, date = date)
        getdata.value = true
    }
    val context = LocalContext.current
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (state.isLoading) {
                    CircularProgressIndicator(color = Color(0xff15420F), strokeWidth = 4.dp)
                } else if (state.notFound) {
                } else if (state.networkError) {
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xffF6F6F6)),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        val painter = rememberImagePainter(
//                            data = "https://booking-apis.larasci.site/" + state.hall!!.photos[0],
                            data = "https://booking-apis.larasci.site/" +state.hall?.photos?.get(0),
                            builder = {
                                crossfade(1000)
                            }
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RectangleShape

                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                painter = painter,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            state.hall?.hall_data?.hall_name?.let { it1 ->
                                Text(
                                    text = it1,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Divider(
                                modifier = Modifier
                                    .height(2.dp)
                                    .background(Color.Gray)
                            )

                            Spacer(modifier = Modifier.height(15.dp))


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                            ) {
                                Text(
                                    text = "Date  ",
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                                    color = Color.Black
                                )
                                Text(
                                    text = date.toString(),
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .weight(1f),
                                    color = Color(0xff15420F),
                                    textAlign = TextAlign.End

                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                            ) {
                                Text(
                                    text = "Start Time  ",
                                    modifier = Modifier
                                        .weight(5f)
                                        .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                                    color = Color.Black
                                )
                                Text(
                                    text = start.toString(),
                                    modifier = Modifier
                                        .align(CenterVertically)
                                        .weight(1f),
                                    color = Color(0xff15420F),
                                    textAlign = TextAlign.End

                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                            ) {
                                Text(
                                    text = "End Time  ",
                                    modifier = Modifier
                                        .weight(5f)
                                        .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                                    color = Color.Black
                                )
                                Text(
                                    text = end.toString(),
                                    modifier = Modifier
                                        .align(CenterVertically)
                                        .weight(1f),
                                    color = Color(0xff15420F),
                                    textAlign = TextAlign.End

                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))

                            Divider(
                                modifier = Modifier
                                    .height(2.dp)
                                    .background(Color.Gray)
                            )
                            Spacer(modifier = Modifier.height(15.dp))


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Is Permenet ? ",
                                    modifier = Modifier
                                        .weight(5f)
                                        .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                                    color = Color.Black
                                )
                                Switch(
                                    checked = isPermenent.value,
                                    onCheckedChange = { isPermenent.value = !isPermenent.value },
                                    modifier = Modifier.weight(1f),
                                    colors =
                                    SwitchDefaults.colors(
                                        checkedThumbColor = Color(0xff15420F), // Set the active color
                                        checkedTrackColor = Color(0xff15420F).copy(alpha = 0.5f), // Set the active track color
                                        uncheckedThumbColor = Color(
                                            android.graphics.Color.parseColor(
                                                "#E2AC80"
                                            )
                                        ), // Set the inactive color
                                        uncheckedTrackColor = Color(
                                            android.graphics.Color.parseColor(
                                                "#E2AC80"
                                            )
                                        ).copy(alpha = 0.5f), // Set the inactive track color
                                    )
                                )
                                Spacer(modifier = Modifier.height(15.dp))

                            }
                            if (isPermenent.value) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "how many times you want to book ? ",
                                        modifier = Modifier
                                            .padding(start = 9.dp, end = 9.dp),
                                        color = Color.Black
                                    )
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = 3.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RoundIconButton(imageVictor = Icons.Default.Remove,
                                            onClick = {
                                                if (viewModle.bookingNum.value > 1)
                                                    viewModle.bookingNum.value =
                                                        viewModle.bookingNum.value - 1
                                            })

                                        Text(

                                            text = viewModle.bookingNum.value.toString(),
                                            modifier = Modifier
                                                .padding(start = 9.dp, end = 9.dp),
                                            color = Color.Black
                                        )
                                        RoundIconButton(imageVictor = Icons.Default.Add,
                                            onClick = {
                                                if (viewModle.bookingNum.value <= 5)
                                                    viewModle.bookingNum.value =
                                                        viewModle.bookingNum.value + 1

                                            })
                                    }

                                }


                            }

                            Spacer(modifier = Modifier.height(15.dp))

                            dropDownMenu(list = state.courses) {
                                state.course = it
                            }
                            Spacer(modifier = Modifier.height(15.dp))

                            Text(text = "If you want to add something ", color = Color.Black)
                            Spacer(modifier = Modifier.height(15.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = state.textRequest.value,
                                    onValueChange = { state.textRequest.value = it },
                                    label = { Text("Put a reason") },
                                    placeholder = { Text("Type here a Reason ") },
                                    maxLines = 10, // Set the maximum number of lines
                                    textStyle = TextStyle(color = Color.Black), // Set the text style
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        textColor = Color.Black, // Set the text color
                                        backgroundColor = Color.White, // Set the background color
                                        cursorColor = Color(0xff15420F), // Set the cursor color
                                        focusedBorderColor = Color(0xff15420F), // Set the focused border color
                                        focusedLabelColor = Color.Black, // Set the focused label color
                                        unfocusedBorderColor = Color(0xff15420F), // Set the unfocused border color
                                        unfocusedLabelColor = Color.Black // Set the unfocused label color
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                onClick = {
                                    if (isPermenent.value){
                                        viewModle.bookingNum.value=0
                                    }
                                    viewModle.pushRequest()
                                    nav.navigate(HallsScreans.HomeScrean.name)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xff15420F),
                                    contentColor = Color.Black
                                ),
                                enabled = !state.isRequested ?: false,
                            ) {
                                Text(
                                    text = if (state.isRequested) {
                                        "You Have Just Booked it"
                                    } else {
                                        "Book Now !"
                                    }, color = Color.White
                                )

                            }
                        }
                    }


                }
            }
        }

    }
}

@Composable
fun dropDownMenu(
    list: List<CourseDtoItem>?,
    onSelected: (course: CourseDtoItem) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = list
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.fillMaxWidth()) {
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
            label = { Text("Choose From Your Courses") },
            trailingIcon = {
                Icon(
                    icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded }, tint = Color(0xff15420F)
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
                disabledBorderColor = Color.Transparent,// Remove border when disabled
                leadingIconColor = Color(0xff15420F)
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
                DropdownMenuItem(onClick = {
                    selectedText = label.course_name
                    onSelected(label)
                    expanded = false
                }) {
                    Text(text = label.course_name, color = Color.Black)
                }
            }
        }
    }

}





