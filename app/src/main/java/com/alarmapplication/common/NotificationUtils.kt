package com.alarmapplication.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.alarmapplication.R
import com.alarmapplication.presentation.uimodel.AlarmUiModel

object NotificationUtils {

    private const val CHANNEL_ID = "AlarmNotificationChannel"

    private const val CHANNEL_NAME = "AlarmNotificationChannel"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                enableLights(true)
                enableVibration(true)
                vibrationPattern = longArrayOf(500, 500)
            }
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showAlarmNotification(context: Context, alarmModel: AlarmUiModel) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Alarm")
            .setContentText("Your alarm at ${alarmModel.hour}:${alarmModel.minute} has gone off")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(500))
        with(NotificationManagerCompat.from(context)) {
            notify(alarmModel.id, builder.build())
        }
    }
}