package com.example.prodigym.domain.useCase.profile

import com.example.prodigym.domain.entity.profile.UserProfileEntity
import com.example.prodigym.domain.entity.profile.WorkoutHistoryEntity
import com.example.prodigym.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
) : ProfileUseCase {

    override suspend fun getUserProfile(): UserProfileEntity = repository.getUserProfile()

    override suspend fun getWorkoutHistory(): List<WorkoutHistoryEntity> = repository.getWorkoutHistory()
}
