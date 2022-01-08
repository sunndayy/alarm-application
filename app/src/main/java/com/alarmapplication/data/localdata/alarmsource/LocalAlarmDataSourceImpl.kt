package com.alarmapplication.data.localdata.alarmsource

import com.alarmapplication.data.localdata.AppDatabase
import com.alarmapplication.data.localdata.dbentity.AlarmDbEntity
import com.alarmapplication.data.localdata.dbentity.toAlarmEntity
import com.alarmapplication.domain.entity.AlarmEntity
import javax.inject.Inject

class LocalAlarmDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : LocalAlarmDataSource {
    override suspend fun getListAlarm(): List<AlarmEntity> {
        return appDatabase.alarmDao().getListAlarm().map { it.toAlarmEntity() }
    }

    override suspend fun addAlarm(alarm: AlarmEntity) {
        return appDatabase.alarmDao().addAlarm(
            AlarmDbEntity(
                id = alarm.id,
                hour = alarm.hour,
                minute = alarm.minute,
                repeatDaily = alarm.repeatDaily
            )
        )
    }

    override suspend fun removeAlarm(alarm: AlarmEntity) {
        return appDatabase.alarmDao().removeAlarm(
            AlarmDbEntity(
                id = alarm.id,
                hour = alarm.hour,
                minute = alarm.minute,
                repeatDaily = alarm.repeatDaily
            )
        )
    }
}