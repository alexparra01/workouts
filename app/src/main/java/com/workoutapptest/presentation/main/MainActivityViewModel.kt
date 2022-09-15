package com.workoutapptest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workoutapptest.domain.models.states.WorkoutState
import com.workoutapptest.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val WORKOUT_ERROR = "can't get workouts"

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: WorkoutRepository): ViewModel() {

    private val _uiState = MutableStateFlow<WorkoutState>(WorkoutState.Success(emptyList()))
    val uiState: StateFlow<WorkoutState> = _uiState

    fun retrieveWorkouts() {
        viewModelScope.launch(Dispatchers.Default) {
            val result = repository.retrieveWorkoutList()
            withContext(Dispatchers.Main){
                if (result.isNotEmpty())
                    _uiState.value = WorkoutState.Success(result)
                else
                    _uiState.value = WorkoutState.Error(WORKOUT_ERROR)
            }
        }
    }
}