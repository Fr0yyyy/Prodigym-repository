package com.example.prodigym.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prodigym.domain.useCase.exercise.ExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseScreenViewModel @Inject constructor(
    private val exercisesUseCase: ExercisesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExerciseUiState>(ExerciseUiState.Loading)
    val uiState: StateFlow<ExerciseUiState> = _uiState.asStateFlow()

    init {
        loadExercises()
    }

    fun loadExercises(limit: Int = 10, offset: Int = 0) {
        viewModelScope.launch {
            _uiState.value = ExerciseUiState.Loading
            try {
                val exercises = exercisesUseCase.getExercises(limit, offset)
                _uiState.value = ExerciseUiState.Success(exercises)
            } catch (e: Exception) {
                _uiState.value = ExerciseUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
