package com.example.prodigym.data.repository

import com.example.prodigym.data.dataSource.exercise.ExerciseDataSource
import com.example.prodigym.data.dataSource.exercise.local.ExerciseDbo
import com.example.prodigym.data.dataSource.exercise.remote.ExerciseDto
import com.example.prodigym.domain.entity.exercise.ExerciseEntity
import com.example.prodigym.domain.repository.ExerciseRepository
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val dataSource: ExerciseDataSource
) : ExerciseRepository {

    override suspend fun getExercises(limit: Int, offset: Int): List<ExerciseEntity> {
        return try {
            val dtos = dataSource.getRemoteExercises(limit, offset)
            val dbos = dtos.map { it.toDbo() }
            dataSource.cacheExercises(dbos)
            dbos.map { it.toEntity() }
        } catch (e: Exception) {
            dataSource.getLocalExercises().map { it.toEntity() }
        }
    }

    override suspend fun getExerciseById(id: String): ExerciseEntity? {
        return try {
            dataSource.getRemoteExerciseById(id).toDbo().toEntity()
        } catch (e: Exception) {
            dataSource.getLocalExercises().find { it.id == id }?.toEntity()
        }
    }

    private fun ExerciseDto.toDbo() = ExerciseDbo(
        id = id,
        name = name,
        bodyPart = bodyPart,
        equipment = equipment,
        gifUrl = gifUrl,
        target = target,
        secondaryMuscles = secondaryMuscles,
        instructions = instructions
    )

    private fun ExerciseDbo.toEntity() = ExerciseEntity(
        id = id,
        name = name,
        bodyPart = bodyPart,
        equipment = equipment,
        gifUrl = gifUrl,
        target = target,
        secondaryMuscles = secondaryMuscles,
        instructions = instructions
    )
}
