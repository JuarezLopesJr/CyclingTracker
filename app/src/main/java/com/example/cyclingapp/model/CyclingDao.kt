package com.example.cyclingapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CyclingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCycling(cycling: Cycling)

    @Delete
    suspend fun deleteCycling(cycling: Cycling)

    @Query("SELECT * FROM cycling_table ORDER BY timestamp DESC")
    fun getAllCyclingSortedByDate(): LiveData<List<Cycling>>

    @Query("SELECT * FROM cycling_table ORDER BY avgSpeedKMH DESC")
    fun getAllCyclingSortedAvgSpeed(): LiveData<List<Cycling>>

    @Query("SELECT * FROM cycling_table ORDER BY distanceInMeters DESC")
    fun getAllCyclingSortedByDistance(): LiveData<List<Cycling>>

    @Query("SELECT * FROM cycling_table ORDER BY timeInMillis DESC")
    fun getAllCyclingSortedByTime(): LiveData<List<Cycling>>

    @Query("SELECT * FROM cycling_table ORDER BY caloriesBurned DESC")
    fun getAllCyclingSortedByCaloriesBurned(): LiveData<List<Cycling>>

    @Query("SELECT SUM(timeInMillis) FROM cycling_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM cycling_table")
    fun getTotalCaloriesBurned(): LiveData<Float>

    @Query("SELECT SUM(distanceInMeters) FROM cycling_table")
    fun getTotalDistanceInMeters(): LiveData<Float>

    @Query("SELECT AVG(avgSpeedKMH) FROM cycling_table")
    fun getTotalAvgSpeed(): LiveData<Long>
}