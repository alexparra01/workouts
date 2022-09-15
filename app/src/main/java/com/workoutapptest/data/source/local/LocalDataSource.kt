package com.workoutapptest.data.source.local

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.workoutapptest.domain.models.Workout
import java.io.IOException
import javax.inject.Inject

private const val WORKOUT_FILE_NAME = "workouts.json"

interface LocalDataSource {
    suspend fun retrieveWorkoutsList(): List<Workout>
}

class LocalDataSourceImpl @Inject constructor(
    private val context: Context,
    private val moshiBuilder: Moshi
    ): LocalDataSource {
    override suspend fun retrieveWorkoutsList(): List<Workout> {
        val jsonString = try {
            context.assets.open(WORKOUT_FILE_NAME).bufferedReader().use { it.readText() }
        }
        catch (ex: IOException) {
            ex.message?.let { Log.e(::LocalDataSourceImpl.name, it) }
            return mutableListOf()
        }
        val listTypes = Types.newParameterizedType(List::class.java, Workout::class.java)
        return moshiBuilder.adapter<List<Workout>>(listTypes).fromJson(jsonString)?: mutableListOf()
    }
}