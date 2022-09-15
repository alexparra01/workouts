package com.workoutapptest.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.workoutapptest.data.source.local.LocalDataSource
import com.workoutapptest.data.source.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(@ApplicationContext context: Context, moshi: Moshi): LocalDataSource{
        return LocalDataSourceImpl(context, moshi)
    }
}