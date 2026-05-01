package com.example.prodigym.data.dataSource.exercise

import com.example.prodigym.data.dataSource.exercise.local.ExerciseDbo
import com.example.prodigym.data.dataSource.exercise.remote.ExerciseDto

interface ExerciseDataSource {
    suspend fun getRemoteExercises(limit: Int, offset: Int): List<ExerciseDto>
    suspend fun getRemoteExerciseById(id: String): ExerciseDto
    suspend fun getLocalExercises(): List<ExerciseDbo>
    suspend fun cacheExercises(exercises: List<ExerciseDbo>)
    suspend fun clearLocalExercises()
}
