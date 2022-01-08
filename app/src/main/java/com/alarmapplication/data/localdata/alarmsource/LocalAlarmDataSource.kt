package com.alarmapplication.data.localdata.alarmsource

import com.alarmapplication.domain.entity.AlarmEntity

interface LocalAlarmDataSource {
    suspend fun getListAlarm(): List<AlarmEntity>

    suspend fun addAlarm(alarm: AlarmEntity)

    suspend fun removeAlarm(alarm: AlarmEntity)
}