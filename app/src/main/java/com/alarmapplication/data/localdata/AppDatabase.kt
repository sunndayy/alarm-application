package com.alarmapplication.data.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alarmapplication.data.localdata.dao.AlarmDao
import com.alarmapplication.data.localdata.dbentity.AlarmDbEntity

@Database(entities = [AlarmDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao
}