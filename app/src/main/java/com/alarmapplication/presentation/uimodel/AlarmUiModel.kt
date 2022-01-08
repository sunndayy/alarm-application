package com.alarmapplication.presentation.uimodel

import com.alarmapplication.domain.entity.AlarmEntity

data class AlarmUiModel(
    val id: Int,
    val hour: Int,
    val minute: Int,
    val repeatDaily: Boolean
)

fun AlarmUiModel.toEntity() : AlarmEntity {
    return AlarmEntity(
        id = this.id,
        hour = this.hour,
        minute = this.minute,
        repeatDaily = this.repeatDaily
    )
}

fun getAlarmUiModel(entity: AlarmEntity) : AlarmUiModel {
    return AlarmUiModel(
        id = entity.id,
        hour = entity.hour,
        minute = entity.minute,
        repeatDaily = entity.repeatDaily
    )
}