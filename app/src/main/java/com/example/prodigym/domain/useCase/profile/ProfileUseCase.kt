package com.example.prodigym.domain.useCase.profile

import com.example.prodigym.domain.entity.profile.UserProfileEntity
import com.example.prodigym.domain.entity.profile.WorkoutHistoryEntity

interface ProfileUseCase {
    suspend fun getUserProfile(): UserProfileEntity
    suspend fun getWorkoutHistory(): List<WorkoutHistoryEntity>
}
