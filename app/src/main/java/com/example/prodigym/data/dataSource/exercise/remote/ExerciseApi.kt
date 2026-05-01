package com.example.prodigym.data.dataSource.exercise.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseApi {

    @GET("exercises")
    suspend fun getExerciseIds(): ExercisesListDto

    @GET("exercises/{id}")
    suspend fun getExerciseById(@Path("id") id: String): ExerciseDto
}
