package com.example.prodigym.data.dataSource.profile

import com.example.prodigym.data.dataSource.profile.local.UserProfileDbo
import com.example.prodigym.data.dataSource.profile.local.WorkoutHistoryDbo

interface ProfileDataSource {
    suspend fun getUserProfile(): UserProfileDbo?
    suspend fun getWorkoutHistory(): List<WorkoutHistoryDbo>
    suspend fun insertUserProfile(profile: UserProfileDbo)
    suspend fun insertWorkoutHistory(workouts: List<WorkoutHistoryDbo>)
}
