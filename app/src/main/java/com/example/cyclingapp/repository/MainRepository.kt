package com.example.cyclingapp.repository

import com.example.cyclingapp.model.Cycling
import com.example.cyclingapp.model.CyclingDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val cyclingDao: CyclingDao
) {
    suspend fun insertCycling(cycling: Cycling) = cyclingDao.insertCycling(cycling)

    suspend fun deleteCycling(cycling: Cycling) = cyclingDao.deleteCycling(cycling)

    fun getAllSortedByDate() = cyclingDao.getAllCyclingSortedByDate()

    fun getAllSortedByDistance() = cyclingDao.getAllCyclingSortedByDistance()

    fun getAllSortedByTimeInMillis() = cyclingDao.getAllCyclingSortedByTime()

    fun getAllSortedByAvgSpeed() = cyclingDao.getAllCyclingSortedAvgSpeed()

    fun getAllSortedByCaloriesBurned() =
        cyclingDao.getAllCyclingSortedByCaloriesBurned()

    fun getTotalDistance() = cyclingDao.getTotalDistanceInMeters()

    fun getTotalTime() = cyclingDao.getTotalTimeInMillis()

    fun getTotalCaloriesBurned() = cyclingDao.getTotalCaloriesBurned()

    fun getTotalAvgSpeed() = cyclingDao.getTotalAvgSpeed()
}