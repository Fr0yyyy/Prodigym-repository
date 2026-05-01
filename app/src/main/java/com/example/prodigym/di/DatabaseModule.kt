package com.example.prodigym.di

import android.content.Context
import androidx.room.Room
import com.example.prodigym.core.Constants
import com.example.prodigym.data.dataSource.exercise.local.ExerciseDao
import com.example.prodigym.data.dataSource.exercise.local.ExerciseDatabase
import com.example.prodigym.data.dataSource.profile.local.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ExerciseDatabase =
        Room.databaseBuilder(
            context,
            ExerciseDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideExerciseDao(database: ExerciseDatabase): ExerciseDao =
        database.exerciseDao()

    @Provides
    @Singleton
    fun provideProfileDao(database: ExerciseDatabase): ProfileDao =
        database.profileDao()
}
