package com.workoutapptest.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.workoutapptest.data.WorkoutDataRepository
import com.workoutapptest.data.factory.WorkoutFactory
import com.workoutapptest.data.source.local.LocalDataSource
import com.workoutapptest.domain.repository.WorkoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideWorkoutFactory(localDataSource: LocalDataSource): WorkoutFactory = WorkoutFactory(localDataSource)

    @Singleton
    @Provides
    fun provideWorkoutRepository(workoutFactory: WorkoutFactory): WorkoutRepository = WorkoutDataRepository(workoutFactory)

}