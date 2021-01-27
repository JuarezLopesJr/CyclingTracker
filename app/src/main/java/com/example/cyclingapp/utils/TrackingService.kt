package com.example.cyclingapp.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.example.cyclingapp.R
import com.example.cyclingapp.utils.Constants.ACTION_PAUSE_SERVICE
import com.example.cyclingapp.utils.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.cyclingapp.utils.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.cyclingapp.utils.Constants.ACTION_STOP_SERVICE
import com.example.cyclingapp.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.example.cyclingapp.utils.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.cyclingapp.utils.Constants.NOTIFICATION_ID
import com.example.cyclingapp.view.MainActivity
import com.google.android.gms.maps.model.LatLng
import timber.log.Timber

typealias Polyline = MutableList<LatLng>
typealias Polylines = MutableList<Polyline>

class TrackingService : LifecycleService() {
    private var isFirstRun = true

    companion object {
        val isTracking = MutableLiveData<Boolean>()
        val pathPoints = MutableLiveData<Polylines>()
        // val pathPoints = MutableLiveData<MutableList<MutableList<LatLng>>>()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    if (isFirstRun) {
                        initForegroundService()
                        isFirstRun = false
                    } else {
                        Timber.d("resuming service...")
                    }
                }

                ACTION_PAUSE_SERVICE -> {
                    Timber.d("paused service")
                }

                ACTION_STOP_SERVICE -> {
                    Timber.d("stopped service")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun initForegroundService() {
        addEmptyPolyline()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            buildNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(
            this,
            NOTIFICATION_CHANNEL_ID
        )
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_bike_directions)
            .setContentTitle("Cycling App")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPending())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun getMainActivityPending() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java).also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

    private fun postInitialValues() {
        isTracking.postValue(false)
        pathPoints.postValue(mutableListOf())
    }

    private fun addEmptyPolyline() = pathPoints.value?.apply {
        add(mutableListOf())
        pathPoints.postValue(this)
    } ?: pathPoints.postValue(mutableListOf(mutableListOf()))

    private fun addPathPoint(location:Location?) {
        location?.let {
            val pos = LatLng(location.latitude, location.longitude)
            pathPoints.value?.apply {
                last().add(pos)
                pathPoints.postValue(this)
            }
        }
    }
// todo https://youtu.be/8ByyVW830nE?list=PLQkwcJG4YTCQ6emtoqSZS2FVwZR9FT3BV&t=11
}