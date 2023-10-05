package com.ainshams.graduation_booking_halls.presentation.screens.core.filter.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.ainshams.graduation_booking_halls.R
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.FilterEvent
import com.ainshams.graduation_booking_halls.presentation.screens.core.filter.FilterViewModel
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonHighlightAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.overlay.BalloonOverlayRoundRect
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val viewModle: FilterViewModel = hiltViewModel()
    val state = viewModle.state
    var massage = ""
    val showBallon = remember {
        mutableStateOf(false)
    }
    val showErrorForEndTime = remember {
        mutableStateOf(false)
    }
    val showErrorForStartTime = remember {
        mutableStateOf(false)
    }
    val showErrorForDateToday = remember {
        mutableStateOf(false)
    }
    val showErrorForDateBefore = remember {
        mutableStateOf(false)
    }
    val showErrorForcapasity = remember {
        mutableStateOf(false)
    }
    val showErrorForHallType = remember {
        mutableStateOf(false)
    }
    val typeOfHall = remember {
        mutableStateOf(0)
    }
    val hallState = remember {
        mutableStateOf(false)
    }
    val simnarState = remember {
        mutableStateOf(false)
    }
    val labState = remember {
        mutableStateOf(false)
    }
    val classState = remember {
        mutableStateOf(false)
    }
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedStartTime by remember {
        mutableStateOf(LocalTime.NOON)
    }
    var isStartAsigned by remember {
        mutableStateOf(false)
    }
    var isEndAsigned by remember {
        mutableStateOf(false)
    }
    var pickedEndTime by remember {
        mutableStateOf(LocalTime.NOON)
    }
    val formatedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .format(pickedDate)
        }
    }
    val formatedStartTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm:ss")
                .format(pickedStartTime)
        }
    }
    val formatedEndTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm:ss")
                .format(pickedEndTime)
        }
    }
    val builder = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setArrowPosition(0.5f)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPadding(12)
        setCornerRadius(8f)
        setBackgroundColorResource(R.color.mygreen)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
        setIsVisibleOverlay(true)
//        setOverlayColorResource(R.color.white)
        setOverlayPaddingResource(R.dimen.tool_tip)
        setBalloonHighlightAnimation(BalloonHighlightAnimation.HEARTBEAT)
        setOverlayShape(
            BalloonOverlayRoundRect(
                R.dimen.tool_tip,
                R.dimen.tool_tip
            )
        )
        setDismissWhenClicked(true)
    }
    val dateDialogState = rememberMaterialDialogState()
    val startTimeDialogState = rememberMaterialDialogState()
    val endTimeDialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = " Save ", textStyle = TextStyle(color = Color(0xff15420F)))
            negativeButton(text = " Cancel! ", textStyle = TextStyle(color = Color(0xff15420F)))
        },
//        backgroundColor = Color(0xFF15420F)
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = " Pick Date for the wanted Hall ",
            allowedDateValidator = {
                it.dayOfWeek.value != 5
            },
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = Color(
                    android.graphics.Color.parseColor(
                        "#E2AC80"
                    )
                ),
                headerTextColor = Color.Black,
                calendarHeaderTextColor = Color.Black,
                dateInactiveBackgroundColor = Color.LightGray,
                dateActiveTextColor = Color.White,
                dateActiveBackgroundColor = Color(0xff15420F)


            )
        ) {
            pickedDate = it
        }
    }
    fun getHallType(): List<String> {
        val list: MutableList<String> = mutableListOf()
        if (hallState.value) {
            list.add("hall")
        }
        if (simnarState.value) {
            list.add("semenar")
        }
        if (labState.value) {
            list.add("lab")
        }
        if (classState.value) {
            list.add("class")
        }
        return list
    }
    MaterialDialog(
        dialogState = startTimeDialogState,
        buttons = {
            positiveButton(text = " Save ", textStyle = TextStyle(color = Color(0xff15420F)))
            negativeButton(text = " Cancel! ", textStyle = TextStyle(color = Color(0xff15420F)))
        }
    ) {
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick start Time for the wanted Hall",
            timeRange = LocalTime.of(8, 0)..LocalTime.of(20, 0),
            is24HourClock = true,
            colors = TimePickerDefaults.colors(
                activeTextColor = Color.White,
                headerTextColor = Color.Black,
                selectorTextColor = Color.White,
                selectorColor = Color(
                    android.graphics.Color.parseColor(
                        "#E2AC80"
                    )
                ),
//                inactivePeriodBackground = Color.Yellow,
                activeBackgroundColor = Color(0xff15420F),
                inactiveTextColor = Color.Black,
                borderColor = Color(0xff15420F),
                inactiveBackgroundColor = Color(0xffF6F6F6)
            )
        )
        {
            isStartAsigned = true
            pickedStartTime = it
        }
    }
    MaterialDialog(
        dialogState = endTimeDialogState,
        buttons = {
            positiveButton(text = " Save ", textStyle = TextStyle(color = Color(0xff15420F)))
            negativeButton(text = " Cancel! ", textStyle = TextStyle(color = Color(0xff15420F)))
        }
    ) {
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick End Time for the wanted Hall",
            timeRange = LocalTime.of(8, 0)..LocalTime.of(20, 0),
            is24HourClock = true,
            colors = TimePickerDefaults.colors(
                activeTextColor = Color.White,
                headerTextColor = Color.Black,
                selectorTextColor = Color.White,
                selectorColor = Color(
                    android.graphics.Color.parseColor(
                        "#E2AC80"
                    )
                ),
//                inactivePeriodBackground = Color.Yellow,
                activeBackgroundColor = Color(0xff15420F),
                inactiveTextColor = Color.Black,
                borderColor = Color(0xff15420F),
                inactiveBackgroundColor = Color(0xffF6F6F6)
            )
        )
        {
            isEndAsigned = true
            pickedEndTime = it
        }
    }
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = 10.dp,
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(4.dp, color = Color(0xff15420F), shape = RectangleShape)
                .background(Color(0xFFECECEC))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFECECEC)),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Balloon(
                        builder = builder,
                        balloonContent = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                text = if (
                                    isStartAsigned &&
                                    isEndAsigned && state.date.equals(
                                        LocalDate.now()
                                    ) && pickedDate.isBefore(LocalDate.now()) && state.hallType.isEmpty() && state.capasity == 0 && state.date.isBefore(
                                        LocalDate.now()
                                    )
                                ) {
                                    "you should Fill All Requirments"
                                } else {
                                    massage
                                },
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                    ) { balloonWindow ->
                        if (showBallon.value) {
                            balloonWindow.showAlignBottom()

                        }

                        Text(text = "Find Available Halls", fontSize = 16.sp, color = Color.Black)


                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Date & Time",
                        color = Color.Black,
                        modifier = Modifier.padding(10.dp)
                    )
                    if (showErrorForDateToday.value || showErrorForDateBefore.value || showErrorForEndTime.value || showErrorForStartTime.value)
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp)
                                .background(
                                    Color(android.graphics.Color.parseColor("#FF1F1F"))
                                )
                                .clip(shape = RoundedCornerShape(20.dp))
                        ) {}
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { dateDialogState.show() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White, // Set the background color
                            contentColor = Color.LightGray // Set the text color
                        )
                    ) {
                        Text(
                            text = if (!pickedDate.isAfter(LocalDate.now())) {
                                "Pick Date"
                            } else {
                                pickedDate.toString()
                            }, textAlign = TextAlign.Start
                        )
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { startTimeDialogState.show() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White, // Set the background color
                            contentColor = Color.LightGray // Set the text color
                        )
                    ) {
                        Text(
                            text = if (!isStartAsigned) {
                                "Pick Start Time"
                            } else {
                                pickedStartTime.toString()
                            }, textAlign = TextAlign.Start
                        )
                    }
                    Spacer(modifier = Modifier.size(3.dp))
                    Button(
                        onClick = { endTimeDialogState.show() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White, // Set the background color
                            contentColor = Color.LightGray // Set the text color
                        )
                    ) {
                        Text(
                            text = if (!isEndAsigned) {
                                "Pick End Time"
                            } else {
                                pickedEndTime.toString()
                            }, textAlign = TextAlign.Start
                        )
                    }
                }/////
                Spacer(modifier = Modifier.size(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Type", color = Color.Black, modifier = Modifier.padding(10.dp))

                    if (showErrorForHallType.value)
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp)
                                .background(
                                    Color(android.graphics.Color.parseColor("#FF1F1F"))
                                )
                                .clip(shape = RoundedCornerShape(50.dp))
                        ) {}
                }
                Spacer(modifier = Modifier.size(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Checkbox(
                        checked = hallState.value,
                        onCheckedChange = { hallState.value = it },
                        colors = CheckboxDefaults.colors(Color(0xff15420F))
                    )
                    Text(
                        text = "Hall ",
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                    Checkbox(
                        checked = labState.value,
                        onCheckedChange = { labState.value = it },
                        colors = CheckboxDefaults.colors(Color(0xff15420F))

                    )
                    Text(
                        text = "Lab ",
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                    Checkbox(
                        checked = classState.value,
                        onCheckedChange = { classState.value = it },
                        colors = CheckboxDefaults.colors(Color(0xff15420F))
                    )
                    Text(
                        text = "Class ",
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                    Checkbox(
                        checked = simnarState.value,
                        onCheckedChange = { simnarState.value = it },
                        colors = CheckboxDefaults.colors(Color(0xff15420F))
                    )
                    Text(
                        text = "Semenar ",
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "capacity", color = Color.Black, modifier = Modifier.padding(10.dp))
                    if (showErrorForcapasity.value)
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp)
                                .background(
                                    Color(android.graphics.Color.parseColor("#FF1F1F"))
                                )
                                .clip(shape = RoundedCornerShape(50.dp))
                        ) {}
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    RadioButton(
                        selected = typeOfHall.value == 50,
                        onClick = { typeOfHall.value = 50 },
                        colors = RadioButtonDefaults.colors(Color(0xff15420F))
                    )
                    Text(
                        text = "50",
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                    RadioButton(
                        selected = typeOfHall.value == 100,
                        onClick = { typeOfHall.value = 100 },
                        colors = RadioButtonDefaults.colors(Color(0xff15420F))
                    )
                    Text(
                        text = "100 ",
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                    RadioButton(
                        selected = typeOfHall.value == 150,
                        onClick = { typeOfHall.value = 150 },
                        colors = RadioButtonDefaults.colors(Color(0xff15420F))
                    )
                    Text(
                        text = "150 ",
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                    RadioButton(
                        selected = typeOfHall.value == 200,
                        onClick = { typeOfHall.value = 200 },
                        colors = RadioButtonDefaults.colors(Color(0xff15420F))
                    )
                    Text(
                        text = "200",
                        color = Color.Black,
                        fontSize = 10.sp
                    )


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    var s = ""


                    Button(
                        onClick = {

                            state.startTime = pickedStartTime
                            state.endTime = pickedEndTime
                            state.date = LocalDate.parse(
                                formatedDate,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            )
                            state.hallType = getHallType()
                            state.capasity = typeOfHall.value

                            if (
                                isStartAsigned &&
                                isEndAsigned && state.date.equals(
                                    LocalDate.now()
                                ) && pickedDate.isBefore(LocalDate.now()) && state.hallType.isEmpty() && state.capasity == 0 && state.date.isBefore(
                                    LocalDate.now()
                                )
                            ) {
                                if (state.startTime.equals(LocalTime.NOON)) {
                                    massage += "start time -"
                                    showErrorForStartTime.value = !showErrorForStartTime.value
                                }
                                if (state.endTime.equals(LocalTime.NOON)) {
                                    massage += "end time -"
                                    showErrorForEndTime.value = !showErrorForEndTime.value
                                }
                                if (state.date.equals(LocalDate.now())) {
                                    massage += "date should be after today -"
                                    showErrorForDateToday.value = !showErrorForDateToday.value
                                }
                                if (state.date.isBefore(LocalDate.now())) {
                                    showErrorForDateBefore.value = showErrorForDateBefore.value
                                }
                                if (state.hallType.isEmpty()) {
                                    massage += "hallType  -"
                                    showErrorForHallType.value = !showErrorForHallType.value
                                }
                                if (state.capasity == 0) {
                                    massage += "capasity  -"

                                    showErrorForcapasity.value = !showErrorForcapasity.value
                                }

                                showBallon.value = !showBallon.value

                            } else {
                                onConfirm()
                                viewModle.OnEvent(FilterEvent.OnSubmitFilter)
                            }
                        },
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff15420F), // Set the background color
                            contentColor = Color.White // Set the text color
                        )
                    ) {
                        Text(text = "Confirm")
                    }

                }


/////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//


//                //date time row
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(12.dp),
//                    horizontalArrangement = Arrangement.SpaceAround
//
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Button(onClick = {
//                            dateDialogState.show()
//                        }) {
//                            Text(text = "Pick Date")
//                        }
//                        Text(text = formatedDate)
//                        if (showErrorForDateToday.value) {
//                            Text(
//                                text = "You Have To Pick Date",
//                                color = Color.Red,
//                                fontWeight = FontWeight.ExtraBold,
//                                fontSize = 12.sp
//                            )
//                        }
//                        if (showErrorForDateBefore.value) {
//                            Text(
//                                text = "You Have To Pick Date after today",
//                                color = Color.Red,
//                                fontWeight = FontWeight.ExtraBold,
//                                fontSize = 8.sp
//                            )
//                        }
//                    }
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Button(onClick = {
//                            startTimeDialogState.show()
//                        }) {
//                            Text(text = "Pick  Start Time")
//
//                        }
//                        Text(text = pickedStartTime.toString())
//                        if (showErrorForStartTime.value) {
//                            Text(
//                                text = "You Have To Pick Start time",
//                                color = Color.Red,
//                                fontWeight = FontWeight.ExtraBold,
//                                fontSize = 12.sp
//                            )
//                        }
//                    }
//                }
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 3.dp)
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Button(onClick = {
//                            endTimeDialogState.show()
//                        }) {
//                            Text(text = "Pick End Time")
//
//                        }
//                        Text(text = pickedEndTime.toString())
//                        if (showErrorForEndTime.value) {
//                            Text(
//                                text = "You Have To Pick End time",
//                                color = Color.Red,
//                                fontWeight = FontWeight.ExtraBold
//                            )
//                        }
//                    }
//                }
//
//
//                Spacer(modifier = Modifier.size(8.dp))
////////////////////////////////////////////////////////////////////////
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "What Kind of Hall ",
//                        modifier = Modifier
//                            .align(Alignment.CenterVertically)
//                            .padding(start = 9.dp, end = 9.dp),
//                        style = TextStyle(fontWeight = FontWeight.Bold)
//                    )
//                }
//                Spacer(modifier = Modifier.size(8.dp))
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    Column() {
//                        Text(
//                            text = "Hall ",
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        Checkbox(
//                            checked = hallState.value,
//                            onCheckedChange = { hallState.value = it },
////                                    colors = CheckboxDefaults.colors(backgroundBotton)
//                        )
//                    }
//                    Column() {
//                        Text(
//                            text = "Lab ",
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        Checkbox(
//                            checked = labState.value,
//                            onCheckedChange = { labState.value = it },
////                                    colors = CheckboxDefaults.colors(backgroundBotton)
//                        )
//                    }
//                    Column() {
//                        Text(
//                            text = "Class ",
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        Checkbox(
//                            checked = classState.value,
//                            onCheckedChange = { classState.value = it },
////                                    colors = CheckboxDefaults.colors(backgroundBotton)
//                        )
//                    }
//                    Column() {
//                        Text(
//                            text = "Semenar ",
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        Checkbox(
//                            checked = simnarState.value,
//                            onCheckedChange = { simnarState.value = it },
////                                    colors = CheckboxDefaults.colors(backgroundBotton)
//                        )
//                    }
//                }
//                if (showErrorForHallType.value) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = "You Have To Pick Hall type",
//                            color = Color.Red,
//                            fontWeight = FontWeight.ExtraBold
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.size(8.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "What is Hall Capasity ",
//                        modifier = Modifier
//                            .align(Alignment.CenterVertically)
//                            .padding(start = 9.dp, end = 9.dp),
//                        style = TextStyle(fontWeight = FontWeight.Bold)
//                    )
//                }
//                Spacer(modifier = Modifier.size(8.dp))
//
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 3.dp)
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    Column() {
//                        Text(
//                            text = "50",
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        RadioButton(
//                            selected = typeOfHall.value == 50,
//                            onClick = { typeOfHall.value = 50 },
////                                        colors = RadioButtonDefaults.colors(backgroundBotton)
//                        )
//                    }
//                    Column() {
//                        Text(
//                            text = "100 ",
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        RadioButton(
//                            selected = typeOfHall.value == 100,
//                            onClick = { typeOfHall.value = 100 },
////                                        colors = RadioButtonDefaults.colors(backgroundBotton)
//                        )
//                    }
//                    Column() {
//                        Text(
//                            text = "150 ",
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        RadioButton(
//                            selected = typeOfHall.value == 150,
//                            onClick = { typeOfHall.value = 150 },
////                                        colors = RadioButtonDefaults.colors(backgroundBotton)
//                        )
//                    }
//                    Column() {
//                        Text(
//                            text = "200",
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        RadioButton(
//                            selected = typeOfHall.value == 200,
//                            onClick = { typeOfHall.value = 200 },
////                                        colors = RadioButtonDefaults.colors(backgroundBotton)
//                        )
//                    }
//                }
//                if (showErrorForcapasity.value) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = "You Have To Pick Capacity",
//                            color = Color.Red,
//                            fontWeight = FontWeight.ExtraBold
//                        )
//                    }
//                }
//
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(30.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Button(
//                        onClick = {
//                            onDismiss()
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color(0xFF666666),
//                            contentColor = Color.White
//                        ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f),
//                        shape = CircleShape
//                    ) {
//                        Text(
//                            text = "Cancel",
//                            style = MaterialTheme.typography.h6,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center,
//                        )
//                    }
//                    Button(
//                        onClick = {
//                            state.startTime = pickedStartTime
//                            state.endTime = pickedEndTime
//                            state.date = LocalDate.parse(
//                                formatedDate,
//                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
//                            )
//                            state.hallType = getHallType()
//                            state.capasity = typeOfHall.value
//
//                            if (
////                                state.startTime.equals(LocalTime.NOON) ||
//                                state.endTime.equals(LocalTime.NOON) || state.date.equals(LocalDate.now()) || state.hallType.isEmpty() || state.capasity == 0 || state.date.isBefore(
//                                    LocalDate.now()
//                                )
//                            ) {
//                                if (state.startTime.equals(LocalTime.NOON)) {
//                                    showErrorForStartTime.value = true
//                                }
//                                if (state.endTime.equals(LocalTime.NOON)) {
//                                    showErrorForEndTime.value = true
//                                }
//                                if (state.date.equals(LocalDate.now())) {
//                                    showErrorForDateToday.value = true
//                                }
//                                if (state.date.isBefore(LocalDate.now())) {
//                                    showErrorForDateBefore.value = true
//                                }
//                                if (state.hallType.isEmpty()) {
//                                    showErrorForHallType.value = true
//                                }
//                                if (state.capasity == 0) {
//                                    showErrorForcapasity.value = true
//                                }
//                            } else {
//                                onConfirm()
//                                showErrorForStartTime.value = false
//                                showErrorForEndTime.value = false
//                                showErrorForDateToday.value = false
//                                showErrorForHallType.value = false
//                                showErrorForcapasity.value = false
//                                showErrorForDateToday.value = false
//                                viewModle.OnEvent(FilterEvent.OnSubmitFilter)
//                            }
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color(0xFF666666),
//                            contentColor = Color.White
//                        ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f),
//                        shape = CircleShape
//                    ) {
//                        Text(
//                            text = "Confirm",
//                            style = MaterialTheme.typography.h6,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center,
//                        )
//                    }
//
//                }
//            }
//
//        }
            }
        }
    }//dialog

}



