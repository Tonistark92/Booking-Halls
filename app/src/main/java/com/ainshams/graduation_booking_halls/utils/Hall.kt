package com.ainshams.graduation_booking_halls.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hall (
    var id:Int,
    var pics :List<Int>? ,
    var name : String,
    var capasity:String,
    var description : String,
    var building : String,
    var floor:Int,
    var status:String,
    var type:String,
    var projector:Boolean,
    var monitor:Boolean,
    var airConditionar:Boolean,
    var isSpecial:Boolean
    ):Parcelable