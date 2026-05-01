package com.example.prodigym.data.dataSource.exercise.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.prodigym.data.dataSource.profile.local.ProfileDao
import com.example.prodigym.data.dataSource.profile.local.UserProfileDbo
import com.example.prodigym.data.dataSource.profile.local.WorkoutHistoryDbo

@Database(
    entities = [ExerciseDbo::class, UserProfileDbo::class, WorkoutHistoryDbo::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun profileDao(): ProfileDao
}
