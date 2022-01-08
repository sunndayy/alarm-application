package com.alarmapplication.data.localdata.dbentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alarmapplication.domain.entity.AlarmEntity

@Entity(tableName = "Alarm")
data class AlarmDbEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "hour") val hour: Int,
    @ColumnInfo(name = "minute") val minute: Int,
    @ColumnInfo(name = "repeat_daily") val repeatDaily: Boolean
)

fun AlarmDbEntity.toAlarmEntity(): AlarmEntity {
    return AlarmEntity(
        id = this.id,
        hour = this.hour,
        minute = this.minute,
        repeatDaily = this.repeatDaily
    )
}