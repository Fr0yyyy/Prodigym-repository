package com.example.prodigym.ui.profile

import com.example.prodigym.domain.entity.profile.UserProfileEntity

sealed class ProfileUiState {
    data object Loading : ProfileUiState()
    data class Success(
        val profile: UserProfileEntity,
        val workoutHistory: List<WorkoutHistoryDisplayItem>
    ) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}

data class WorkoutHistoryDisplayItem(
    val id: String,
    val name: String,
    val date: String,
    val duration: String,
    val volume: String
)
