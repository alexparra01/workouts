package com.workoutapptest.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Workout(
    val name: String,
    val subcategory_name: String,
    val circuit_count: Int,
    val exercise_count: Int,
    val estimated_duration: String,
    val image: String
)
