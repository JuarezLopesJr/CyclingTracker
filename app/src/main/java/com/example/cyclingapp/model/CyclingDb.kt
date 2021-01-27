package com.example.cyclingapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cyclingapp.utils.Converters

@Database(entities = [Cycling::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CyclingDb : RoomDatabase() {

    abstract fun getCyclingDao(): CyclingDao
}