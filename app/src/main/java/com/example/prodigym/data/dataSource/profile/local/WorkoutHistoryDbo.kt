package com.example.prodigym.data.dataSource.profile.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_history")
data class WorkoutHistoryDbo(
    @PrimaryKey val id: String,
    val name: String,
    val date: String,
    val durationMinutes: Int,
    val totalVolumeKg: Int
)
