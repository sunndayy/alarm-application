package com.alarmapplication.common

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.alarmapplication.presentation.receiver.AlarmReceiver
import com.alarmapplication.presentation.uimodel.AlarmUiModel
import java.util.*

object AlarmUtils {

    const val ALARM_ARG_ID = "AlarmArgumentId"

    const val ALARM_ARG_HOUR = "AlarmArgumentHour"

    const val ALARM_ARG_MINUTE = "AlarmArgumentMinute"

    const val ALARM_ARG_REPEAT = "AlarmArgumentRepeatDaily"

    private const val DAY_INTERVAL = 24 * 60 * 60 * 1000L

    fun scheduleAlarm(context: Context, alarmModel: AlarmUiModel) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(ALARM_ARG_ID, alarmModel.id)
            putExtra(ALARM_ARG_HOUR, alarmModel.hour)
            putExtra(ALARM_ARG_MINUTE, alarmModel.minute)
            putExtra(ALARM_ARG_REPEAT, alarmModel.repeatDaily)
        }
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent = PendingIntent.getBroadcast(
            context, alarmModel.id, intent, flag
        )
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, alarmModel.hour)
            set(Calendar.MINUTE, alarmModel.minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
        }
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (alarmModel.repeatDaily) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                DAY_INTERVAL,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }

    fun cancelAlarm(context: Context, alarmModel: AlarmUiModel) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent = PendingIntent.getBroadcast(
            context, alarmModel.id, intent, flag
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }
}