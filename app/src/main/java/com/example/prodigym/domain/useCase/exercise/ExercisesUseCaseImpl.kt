package com.example.prodigym.domain.useCase.exercise

import com.example.prodigym.domain.entity.exercise.ExerciseEntity
import com.example.prodigym.domain.repository.ExerciseRepository
import javax.inject.Inject

class ExercisesUseCaseImpl @Inject constructor(
    private val repository: ExerciseRepository
) : ExercisesUseCase {

    override suspend fun getExercises(limit: Int, offset: Int): List<ExerciseEntity> {
        return repository.getExercises(limit, offset)
    }

    override suspend fun getExerciseById(id: String): ExerciseEntity? {
        return repository.getExerciseById(id)
    }
}
