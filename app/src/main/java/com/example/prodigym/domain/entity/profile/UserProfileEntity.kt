package com.example.prodigym.domain.entity.profile

data class UserProfileEntity(
    val id: String,
    val name: String,
    val performanceTier: String,
    val avatarUrl: String?,
    val totalWorkouts: Int,
    val activeStreak: Int,
    val personalRecords: Int
)
