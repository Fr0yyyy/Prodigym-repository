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
        withContext(Dispatchers.IO) { api.getExercises(limit, offset) }

    override suspend fun getRemoteExerciseById(id: String): ExerciseDto =
        withContext(Dispatchers.IO) { api.getExerciseById(id) }

    override suspend fun getLocalExercises(): List<ExerciseDbo> =
        withContext(Dispatchers.IO) { dao.getAllExercises() }

    override suspend fun cacheExercises(exercises: List<ExerciseDbo>) =
        withContext(Dispatchers.IO) { dao.insertExercises(exercises) }

    override suspend fun clearLocalExercises() =
        withContext(Dispatchers.IO) { dao.clearExercises() }
}
