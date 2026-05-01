package com.example.prodigym.data.dataSource.profile

import com.example.prodigym.data.dataSource.profile.local.ProfileDao
import com.example.prodigym.data.dataSource.profile.local.UserProfileDbo
import com.example.prodigym.data.dataSource.profile.local.WorkoutHistoryDbo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val dao: ProfileDao
) : ProfileDataSource {

    override suspend fun getUserProfile(): UserProfileDbo? =
        withContext(Dispatchers.IO) { dao.getUserProfile() }

    override suspend fun getWorkoutHistory(): List<WorkoutHistoryDbo> =
        withContext(Dispatchers.IO) { dao.getWorkoutHistory() }

    override suspend fun insertUserProfile(profile: UserProfileDbo) =
        withContext(Dispatchers.IO) { dao.insertUserProfile(profile) }

    override suspend fun insertWorkoutHistory(workouts: List<WorkoutHistoryDbo>) =
        withContext(Dispatchers.IO) { dao.insertWorkoutHistory(workouts) }
}
