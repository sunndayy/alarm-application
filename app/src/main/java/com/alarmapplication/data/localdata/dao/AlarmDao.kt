package com.alarmapplication.data.localdata.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alarmapplication.data.localdata.dbentity.AlarmDbEntity

@Dao
interface AlarmDao {

    @Query("SELECT * FROM Alarm")
    suspend fun getListAlarm(): List<AlarmDbEntity>

    @Insert
    suspend fun addAlarm(alarmDbEntity: AlarmDbEntity)

    @Delete
    suspend fun removeAlarm(alarmDbEntity: AlarmDbEntity)
}