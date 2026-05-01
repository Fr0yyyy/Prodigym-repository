package com.example.prodigym.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prodigym.domain.useCase.profile.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            try {
                val profile = profileUseCase.getUserProfile()
                val history = profileUseCase.getWorkoutHistory().map { entity ->
                    WorkoutHistoryDisplayItem(
                        id = entity.id,
                        name = entity.name,
                        date = entity.date,
                        duration = formatDuration(entity.durationMinutes),
                        volume = formatVolume(entity.totalVolumeKg)
                    )
                }
                _uiState.value = ProfileUiState.Success(profile, history)
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun formatDuration(minutes: Int): String {
        val h = minutes / 60
        val m = minutes % 60
        return if (h > 0) "${h}h ${m}m" else "${m}m"
    }

    private fun formatVolume(kg: Int): String =
        "%,d kg".format(kg)
}
