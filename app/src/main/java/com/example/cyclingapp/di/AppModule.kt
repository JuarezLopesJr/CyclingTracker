package com.example.cyclingapp.di

import android.content.Context
import androidx.room.Room
import com.example.cyclingapp.model.CyclingDb
import com.example.cyclingapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCyclingDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CyclingDb::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideCyclingDao(db: CyclingDb) = db.getCyclingDao()
}