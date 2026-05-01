package com.example.prodigym.di

import com.example.prodigym.data.dataSource.exercise.ExerciseDataSource
import com.example.prodigym.data.dataSource.exercise.ExerciseDataSourceImpl
import com.example.prodigym.data.dataSource.profile.ProfileDataSource
import com.example.prodigym.data.dataSource.profile.ProfileDataSourceImpl
import com.example.prodigym.data.repository.ExerciseRepositoryImpl
import com.example.prodigym.data.repository.ProfileRepositoryImpl
import com.example.prodigym.domain.repository.ExerciseRepository
import com.example.prodigym.domain.repository.ProfileRepository
import com.example.prodigym.domain.useCase.exercise.ExercisesUseCase
import com.example.prodigym.domain.useCase.exercise.ExercisesUseCaseImpl
import com.example.prodigym.domain.useCase.profile.ProfileUseCase
import com.example.prodigym.domain.useCase.profile.ProfileUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun bindExerciseDataSource(impl: ExerciseDataSourceImpl): ExerciseDataSource

    @Binds @Singleton
    abstract fun bindExerciseRepository(impl: ExerciseRepositoryImpl): ExerciseRepository

    @Binds @Singleton
    abstract fun bindExercisesUseCase(impl: ExercisesUseCaseImpl): ExercisesUseCase

    @Binds @Singleton
    abstract fun bindProfileDataSource(impl: ProfileDataSourceImpl): ProfileDataSource

    @Binds @Singleton
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds @Singleton
    abstract fun bindProfileUseCase(impl: ProfileUseCaseImpl): ProfileUseCase
}
