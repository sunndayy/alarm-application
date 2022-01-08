package com.alarmapplication.domain.entity

data class AlarmEntity(
    val id: Int,
    val hour: Int,
    val minute: Int,
    val repeatDaily: Boolean
)