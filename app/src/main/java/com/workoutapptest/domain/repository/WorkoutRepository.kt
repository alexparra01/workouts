package com.workoutapptest.domain.repository

import com.workoutapptest.domain.models.Workout

interface WorkoutRepository {
    suspend fun retrieveWorkoutList(): List<Workout>
}