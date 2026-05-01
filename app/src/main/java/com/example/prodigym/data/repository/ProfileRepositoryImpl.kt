package com.example.prodigym.data.repository

import com.example.prodigym.data.dataSource.profile.ProfileDataSource
import com.example.prodigym.data.dataSource.profile.local.UserProfileDbo
import com.example.prodigym.data.dataSource.profile.local.WorkoutHistoryDbo
import com.example.prodigym.domain.entity.profile.UserProfileEntity
import com.example.prodigym.domain.entity.profile.WorkoutHistoryEntity
import com.example.prodigym.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
) : ProfileRepository {

    override suspend fun getUserProfile(): UserProfileEntity {
        val dbo = dataSource.getUserProfile() ?: run {
            val default = defaultProfileDbo()
            dataSource.insertUserProfile(default)
            default
        }
        return dbo.toEntity()
    }

    override suspend fun getWorkoutHistory(): List<WorkoutHistoryEntity> {
        val history = dataSource.getWorkoutHistory()
        if (history.isEmpty()) {
            val defaults = defaultWorkoutHistory()
            dataSource.insertWorkoutHistory(defaults)
            return defaults.map { it.toEntity() }
        }
        return history.map { it.toEntity() }
    }

    private fun defaultProfileDbo() = UserProfileDbo(
        id = "1",
        name = "ALEX RIVERA",
        performanceTier = "PRO PERFORMANCE LEVEL",
        avatarUrl = null,
        totalWorkouts = 124,
        activeStreak = 12,
        personalRecords = 42
    )

    private fun defaultWorkoutHistory() = listOf(
        WorkoutHistoryDbo("4", "Heavy Leg Day A", "Oct 24", 72, 18450),
        WorkoutHistoryDbo("3", "Push Hypertrophy", "Oct 22", 58, 12200),
        WorkoutHistoryDbo("2", "Pull - Back Focus", "Oct 20", 65, 14800),
        WorkoutHistoryDbo("1", "Conditioning & Core", "Oct 18", 45, 2100)
    )

    private fun UserProfileDbo.toEntity() = UserProfileEntity(
        id = id,
        name = name,
        performanceTier = performanceTier,
        avatarUrl = avatarUrl,
        totalWorkouts = totalWorkouts,
        activeStreak = activeStreak,
        personalRecords = personalRecords
    )

    private fun WorkoutHistoryDbo.toEntity() = WorkoutHistoryEntity(
        id = id,
        name = name,
        date = date,
        durationMinutes = durationMinutes,
        totalVolumeKg = totalVolumeKg
    )
}
