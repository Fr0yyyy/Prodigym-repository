package com.example.prodigym.data.dataSource.exercise.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    suspend fun getAllExercises(): List<ExerciseDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseDbo>)

    @Query("DELETE FROM exercises")
    suspend fun clearExercises()
}
