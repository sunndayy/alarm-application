package com.alarmapplication

import android.app.Application
import com.alarmapplication.common.NotificationUtils
import com.alarmapplication.di.component.AppComponent

class AndroidApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this)
        NotificationUtils.createNotificationChannel(this)
    }
}