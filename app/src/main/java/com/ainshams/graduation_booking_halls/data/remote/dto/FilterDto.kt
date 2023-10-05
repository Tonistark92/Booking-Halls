package com.ainshams.graduation_booking_halls.data.remote.dto

data class FilterDto(
    val building_place: Int,
    val capacity: Int,
    val description_place: String,
    val floor_place: Int,
    val hall_id: Int,
    val hall_name: String,
    val hall_photos: List<String>,
    val is_special: Int,
    val status: String,
    val type: String
)