package com.alarmapplication.data.repository

import com.alarmapplication.data.localdata.alarmsource.LocalAlarmDataSource
import com.alarmapplication.domain.entity.AlarmEntity
import com.alarmapplication.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val localAlarmDataSource: LocalAlarmDataSource
) : AlarmRepository {
    override suspend fun getListAlarm(): List<AlarmEntity> {
        return localAlarmDataSource.getListAlarm()
    }

    override suspend fun addAlarm(alarm: AlarmEntity) {
        localAlarmDataSource.addAlarm(alarm)
    }

    override suspend fun removeAlarm(alarm: AlarmEntity) {
        localAlarmDataSource.removeAlarm(alarm)
    }
}