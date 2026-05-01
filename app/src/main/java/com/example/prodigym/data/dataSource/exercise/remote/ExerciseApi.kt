package com.example.prodigym.data.dataSource.exercise.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExerciseApi {

    @GET("exercises")
    suspend fun getExercises(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): List<ExerciseDto>

    @GET("exercises/{id}")
    suspend fun getExerciseById(@Path("id") id: String): ExerciseDto
}
