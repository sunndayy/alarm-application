package com.alarmapplication.presentation.uimodel

data class AlarmListItemModel(
    val id: Int,
    val time: String,
    val repeatDaily: String
)

const val NO_REPEAT = "No repeat"
const val REPEAT_DAILY = "Repeat daily"

fun getAlarmListItemModel(item: AlarmUiModel): AlarmListItemModel {
    return AlarmListItemModel(
        id = item.id,
        time = "${item.hour}:${item.minute}",
        repeatDaily = if (item.repeatDaily) REPEAT_DAILY else NO_REPEAT
    )
}

fun AlarmListItemModel.toAlarmUiModel(): AlarmUiModel {
    val list = time.split(':')
    val hour = list[0].toInt()
    val minute = list[1].toInt()
    return AlarmUiModel(
        id = this.id,
        hour = hour,
        minute = minute,
        repeatDaily = this.repeatDaily == REPEAT_DAILY
    )
}