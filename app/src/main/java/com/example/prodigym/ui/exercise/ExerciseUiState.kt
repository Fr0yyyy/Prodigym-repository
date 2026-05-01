package com.example.prodigym.ui.exercise

import com.example.prodigym.domain.entity.exercise.ExerciseEntity

sealed class ExerciseUiState {
    data object Loading : ExerciseUiState()
    data class Success(val exercises: List<ExerciseEntity>) : ExerciseUiState()
    data class Error(val message: String) : ExerciseUiState()
}
