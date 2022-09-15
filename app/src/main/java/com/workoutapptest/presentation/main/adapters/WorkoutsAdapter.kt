package com.workoutapptest.presentation.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.workoutapptest.databinding.WorkoutItemBinding
import com.workoutapptest.domain.models.Workout

class WorkoutsAdapter(
    private val workouts: List<Workout>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = WorkoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is WorkoutViewHolder -> {
                holder.bind(workouts[position])
            }
        }
    }

    override fun getItemCount(): Int = workouts.size

    inner class WorkoutViewHolder(private val binding: WorkoutItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(workout: Workout){
            Glide.with(binding.root).load(workout.image).centerCrop().into(binding.workoutImageView)
            binding.sectionTitle.text = workout.subcategory_name
            binding.workoutTitle.text = workout.name
            binding.circuitsTextValue.text = workout.circuit_count.toString()
            binding.exercisesTextValue.text = workout.exercise_count.toString()
            binding.minsTextValue.text = workout.estimated_duration
        }
    }

}