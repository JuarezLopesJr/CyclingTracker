package com.example.cyclingapp.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cycling_table")
data class Cycling(
    var img: Bitmap? = null,
    var timestamp: Long = 0L,
    var avgSpeedKMH: Float = 0f,
    var distanceInMeters: Float = 0f,
    var timeInMillis: Long = 0L,
    var caloriesBurned: Float = 0f
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
