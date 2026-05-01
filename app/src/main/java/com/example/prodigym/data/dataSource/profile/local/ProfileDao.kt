package com.example.prodigym.data.dataSource.profile.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {

    @Query("SELECT * FROM user_profile LIMIT 1")
    suspend fun getUserProfile(): UserProfileDbo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(profile: UserProfileDbo)

    @Query("SELECT * FROM workout_history ORDER BY id DESC")
    suspend fun getWorkoutHistory(): List<WorkoutHistoryDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutHistory(workouts: List<WorkoutHistoryDbo>)
}
