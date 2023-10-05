package com.ainshams.graduation_booking_halls.data.remote.dto

data class HallDataX(
    val building_place: Int,
    val capacity: Int,
    val description_place: String,
    val floor_place: Int,
    val hall_id: Int,
    val hall_name: String,
    val has_air_condition: Int,
    val has_monitor: Int,
    val has_projector: Int,
    val is_special: Int,
    val status: String,
    val type: String
)