package com.ainshams.graduation_booking_halls.presentation.screens.core.home

sealed class HomeEvent {
    object Refresh: HomeEvent()
    data class OnSearchQueryChange(val query: String): HomeEvent()
}