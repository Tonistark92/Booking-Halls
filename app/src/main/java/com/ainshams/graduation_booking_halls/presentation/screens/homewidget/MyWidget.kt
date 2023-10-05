package com.ainshams.graduation_booking_halls.presentation.screens.homewidget

import androidx.compose.material.ButtonDefaults
import androidx.glance.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider


class MyWidget : GlanceAppWidget() {

    // data store keys ..
    companion object {

        // name keys ...
        val DATA_STORE_NAME_KEY = stringPreferencesKey("widget_name_key")
        val GLANCE_PARAMETER_KEY = ActionParameters.Key<String>("widget_name_key")
        const val NAME_DEFAULT_VALUE = "NO NAME YET"    // default value if parameter not exists

        // click counter keys ..
//        val DATA_STORE_COUNT_KEY = intPreferencesKey("count_key")
//        val GLANCE_COUNT_KEY = ActionParameters.Key<Int>("glance_count_key")
//        const val DEFAULT_COUNT_VALUE = 0

        //my data
        val DATA_REQUEST_KEY = intPreferencesKey("request_state_key")
        val GLANCE_STATE_KEY = ActionParameters.Key<Int>("state_name_key")
        const val STATE_DEFAULT_VALUE = 0  // default value if state not exists

        val DATA_START_KEY = stringPreferencesKey("request_start_key")
        val GLANCE_START_KEY = ActionParameters.Key<String>("start_name_key")
        const val START_DEFAULT_VALUE = ""  // default value if state not exists

        val DATA_END_KEY = stringPreferencesKey("request_end_key")
        val GLANCE_END_KEY = ActionParameters.Key<String>("end_name_key")
        const val END_DEFAULT_VALUE = ""  // default value if state not exists

        val DATA_DATE_KEY = stringPreferencesKey("request_DATE_key")
        val GLANCE_DATE_KEY = ActionParameters.Key<String>("DATE_name_key")
        const val DATE_DEFAULT_VALUE = ""  // default value if state not exists

        val DATA_REASON_KEY = stringPreferencesKey("request_reason_key")
        val GLANCE_REASON_KEY = ActionParameters.Key<String>("reason_name_key")
        const val REASON_DEFAULT_VALUE = ""  // default value if state not exists
    }

    @Composable
    override fun Content() {

        val name = currentState(key = DATA_STORE_NAME_KEY) ?: NAME_DEFAULT_VALUE
//        val count = currentState(key = DATA_STORE_COUNT_KEY) ?: DEFAULT_COUNT_VALUE
        val state = currentState(key = DATA_REQUEST_KEY) ?: STATE_DEFAULT_VALUE
        val start = currentState(key = DATA_START_KEY) ?: START_DEFAULT_VALUE
        val end = currentState(key = DATA_END_KEY) ?: END_DEFAULT_VALUE
        val date = currentState(key = DATA_DATE_KEY) ?: DATE_DEFAULT_VALUE
        val reason = currentState(key = DATA_REASON_KEY) ?: REASON_DEFAULT_VALUE
        val textTitleColor = Color.Black
        val textValueColor = Color(0xff15420F)

        // glance box ..
        Box(
            modifier = GlanceModifier.fillMaxSize()
                .background(color = Color.White)

        ) {
            androidx.glance.layout.Column(
                modifier = GlanceModifier.fillMaxSize().padding(top = 8.dp),
                horizontalAlignment = Alignment.Horizontal.Start
            ) {
                Text(
                    text = "Your Last Request : ",
                    style = TextStyle(
                        color = ColorProvider(textTitleColor),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,

                        ),
                    modifier = GlanceModifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = GlanceModifier.height(6.dp))
                Text(
                    text = "Date :   $date",
                    style = TextStyle(
                        color = ColorProvider(textTitleColor),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = GlanceModifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "Start Time :   $start",
                    style = TextStyle(
                        color = ColorProvider(textTitleColor),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = GlanceModifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "End Time :   $end",
                    style = TextStyle(
                        color = ColorProvider(textTitleColor),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = GlanceModifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "With Reason  :  $reason ",
                    style = TextStyle(
                        color = ColorProvider(textTitleColor),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,

                        ),
                    modifier = GlanceModifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "Request State  :   " + if (state == 0) {
                        "Waiting"
                    } else if (state == -1) {
                        " Request Denied"
                    } else {
                        "Accepted"
                    },
                    style = TextStyle(
                        color = if (state == 0) {
                            ColorProvider(Color.LightGray)
                        } else if (state == -1) {
                            ColorProvider(Color.Red)
                        } else {
                            ColorProvider(Color.Green)
                        },
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = GlanceModifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = GlanceModifier.height(10.dp))
                Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Horizontal.CenterHorizontally
                ) {
                    Button(
                        text = "Refresh",
                        onClick = actionRunCallback<MyActionCallback>(
                            actionParametersOf(
                                // Create this using the infix function (to), you can add more ...
//                            GLANCE_PARAMETER_KEY to name,
//                            GLANCE_COUNT_KEY to count,
                                GLANCE_STATE_KEY to state,
                                GLANCE_START_KEY to start,
                                GLANCE_END_KEY to end,
                                GLANCE_DATE_KEY to date,
                                GLANCE_REASON_KEY to reason
                            )
                        ),
                        modifier = GlanceModifier.background(Color.DarkGray),
                        style = TextStyle(color = ColorProvider(Color.White)),
                    )
                }


            }
        }


    }

}

// Widget provider ..
class MyWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = MyWidget()

}