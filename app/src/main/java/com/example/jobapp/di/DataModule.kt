package com.example.jobapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.api.JobApi
import com.example.data.room.JobDatabase
import com.example.data.room.VacancyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApi(): JobApi = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JobApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): JobDatabase =
        Room.databaseBuilder(
            context,
            JobDatabase::class.java,
            "job_database"
        )
            .build()

    @Provides
    @Singleton
    fun provideVacancyDao(database: JobDatabase): VacancyDao = database.getVacanciesDao()
}