package com.workoutapptest.domain.models.states

import com.workoutapptest.domain.models.Workout

sealed interface WorkoutState {
    data class Success(val workouts: List<Workout>): WorkoutState
    data class Error(val message: String): WorkoutState
}
