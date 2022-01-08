package com.alarmapplication.domain.usecase

import com.alarmapplication.domain.entity.AlarmEntity
import com.alarmapplication.domain.repository.AlarmRepository
import javax.inject.Inject

class GetListAlarmUseCase @Inject constructor(
    private val repository: AlarmRepository
) {
    suspend operator fun invoke(): List<AlarmEntity> {
        return repository.getListAlarm()
    }
}