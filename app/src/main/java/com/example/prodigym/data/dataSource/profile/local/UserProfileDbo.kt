package com.example.prodigym.data.dataSource.profile.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileDbo(
    @PrimaryKey val id: String,
    val name: String,
    val performanceTier: String,
    val avatarUrl: String?,
    val totalWorkouts: Int,
    val activeStreak: Int,
    val personalRecords: Int
)
