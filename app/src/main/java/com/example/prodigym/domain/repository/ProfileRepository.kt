package com.example.prodigym.domain.repository

import com.example.prodigym.domain.entity.profile.UserProfileEntity
import com.example.prodigym.domain.entity.profile.WorkoutHistoryEntity

interface ProfileRepository {
    suspend fun getUserProfile(): UserProfileEntity
    suspend fun getWorkoutHistory(): List<WorkoutHistoryEntity>
}
