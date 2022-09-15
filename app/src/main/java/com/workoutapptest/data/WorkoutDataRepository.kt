package com.workoutapptest.data

import com.workoutapptest.data.factory.WorkoutFactory
import com.workoutapptest.domain.models.Workout
import com.workoutapptest.domain.repository.WorkoutRepository
import javax.inject.Inject


class WorkoutDataRepository @Inject constructor(private val factory: WorkoutFactory) : WorkoutRepository {
    override suspend fun retrieveWorkoutList(): List<Workout> {
        return factory.retrieveWorkoutFactory()
    }
}