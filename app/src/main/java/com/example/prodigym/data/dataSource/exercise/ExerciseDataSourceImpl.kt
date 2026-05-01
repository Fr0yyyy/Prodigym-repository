package com.example.prodigym.data.dataSource.exercise

import com.example.prodigym.data.dataSource.exercise.local.ExerciseDao
import com.example.prodigym.data.dataSource.exercise.local.ExerciseDbo
import com.example.prodigym.data.dataSource.exercise.remote.ExerciseApi
import com.example.prodigym.data.dataSource.exercise.remote.ExerciseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExerciseDataSourceImpl @Inject constructor(
    private val api: ExerciseApi,
    private val dao: ExerciseDao
) : ExerciseDataSource {

    override suspend fun getRemoteExercises(limit: Int, offset: Int): List<ExerciseDto> =
        withContext(Dispatchers.IO) {
            val allIds = api.getExerciseIds().exerciseIds
            allIds.drop(offset).take(limit).map { id -> id.toDto() }
        }

    override suspend fun getRemoteExerciseById(id: String): ExerciseDto =
        withContext(Dispatchers.IO) { api.getExerciseById(id) }

    override suspend fun getLocalExercises(): List<ExerciseDbo> =
        withContext(Dispatchers.IO) { dao.getAllExercises() }

    override suspend fun cacheExercises(exercises: List<ExerciseDbo>) =
        withContext(Dispatchers.IO) { dao.insertExercises(exercises) }

    override suspend fun clearLocalExercises() =
        withContext(Dispatchers.IO) { dao.clearExercises() }

    private fun String.toDto() = ExerciseDto(
        id = this,
        name = replace("_", " ").replace("-", " - "),
        bodyPart = inferBodyPart(this),
        equipment = "N/A",
        gifUrl = "",
        target = "N/A",
        secondaryMuscles = emptyList(),
        instructions = emptyList()
    )

    private fun inferBodyPart(id: String): String {
        val lower = id.lowercase()
        return when {
            lower.contains("barbell") || lower.contains("dumbbell") || lower.contains("bench") -> "chest"
            lower.contains("squat") || lower.contains("leg") || lower.contains("hamstring") || lower.contains("calf") -> "upper legs"
            lower.contains("pull") || lower.contains("row") || lower.contains("lat") -> "back"
            lower.contains("curl") || lower.contains("bicep") -> "upper arms"
            lower.contains("tricep") || lower.contains("pushdown") -> "upper arms"
            lower.contains("shoulder") || lower.contains("delt") -> "shoulders"
            lower.contains("crunch") || lower.contains("sit") || lower.contains("ab") -> "waist"
            lower.contains("cable") -> "back"
            else -> "general"
        }
    }
}
