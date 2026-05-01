package com.example.prodigym.domain.entity.profile

data class WorkoutHistoryEntity(
    val id: String,
    val name: String,
    val date: String,
    val durationMinutes: Int,
    val totalVolumeKg: Int
)
