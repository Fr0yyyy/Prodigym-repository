package com.example.prodigym.data.dataSource.exercise.remote

import com.google.gson.annotations.SerializedName

data class ExercisesListDto(
    @SerializedName("excercises_ids") val exerciseIds: List<String>
)

data class ExerciseDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("bodyPart") val bodyPart: String,
    @SerializedName("equipment") val equipment: String,
    @SerializedName("gifUrl") val gifUrl: String,
    @SerializedName("target") val target: String,
    @SerializedName("secondaryMuscles") val secondaryMuscles: List<String>,
    @SerializedName("instructions") val instructions: List<String>
)
