package com.alarmapplication.common

import android.os.Build
import android.widget.TimePicker

object TimePickerUtils {

    fun getHour(timePicker: TimePicker): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.hour
        } else {
            timePicker.currentHour
        }
    }

    fun getMinute(timePicker: TimePicker): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.minute
        } else {
            timePicker.currentMinute
        }
    }
}