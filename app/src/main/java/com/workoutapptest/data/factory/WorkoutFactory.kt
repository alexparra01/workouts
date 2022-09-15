package com.workoutapptest.data.factory

import com.workoutapptest.data.source.local.LocalDataSource
import com.workoutapptest.domain.models.Workout
import javax.inject.Inject

class WorkoutFactory @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend fun retrieveWorkoutFactory(): List<Workout> = localDataSource.retrieveWorkoutsList()
}