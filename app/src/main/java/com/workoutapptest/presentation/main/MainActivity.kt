package com.workoutapptest.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.workoutapptest.databinding.ActivityMainBinding
import com.workoutapptest.domain.models.states.WorkoutState
import com.workoutapptest.presentation.main.adapters.WorkoutsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.retrieveWorkouts()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when(state){
                        is WorkoutState.Success -> {
                            val adapter = WorkoutsAdapter(workouts = state.workouts)
                            val manager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                            binding.workoutsRecyclerview.layoutManager = manager
                            binding.workoutsRecyclerview.adapter = adapter
                        }
                        is WorkoutState.Error -> {
                            Snackbar.make(binding.root, state.message, Snackbar.LENGTH_SHORT)
                        }
                    }
                }
            }
        }

    }
}