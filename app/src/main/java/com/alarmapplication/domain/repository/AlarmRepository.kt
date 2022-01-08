package com.alarmapplication.domain.repository

import com.alarmapplication.domain.entity.AlarmEntity

interface AlarmRepository {
    suspend fun getListAlarm(): List<AlarmEntity>

    suspend fun addAlarm(alarm: AlarmEntity)

    suspend fun removeAlarm(alarm: AlarmEntity)
}