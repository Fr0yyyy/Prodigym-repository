package com.example.prodigym.domain.repository

import com.example.prodigym.domain.entity.exercise.ExerciseEntity

interface ExerciseRepository {
    suspend fun getExercises(limit: Int = 10, offset: Int = 0): List<ExerciseEntity>
    suspend fun getExerciseById(id: String): ExerciseEntity?
}
