package com.alarmapplication.di.module

import com.alarmapplication.data.localdata.alarmsource.LocalAlarmDataSource
import com.alarmapplication.data.localdata.alarmsource.LocalAlarmDataSourceImpl
import com.alarmapplication.data.repository.AlarmRepositoryImpl
import com.alarmapplication.domain.repository.AlarmRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    @Binds
    @Singleton
    fun bindAlarmRepository(alarmRepository: AlarmRepositoryImpl): AlarmRepository

    @Binds
    @Singleton
    fun bindLocalAlarmDataSource(localAlarmDataSource: LocalAlarmDataSourceImpl): LocalAlarmDataSource
}