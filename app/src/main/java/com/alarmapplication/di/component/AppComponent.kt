package com.alarmapplication.di.component

import android.app.Application
import com.alarmapplication.presentation.acitivity.SetUpAlarmActivity
import com.alarmapplication.di.module.AppModule
import com.alarmapplication.di.module.DataModule
import com.alarmapplication.di.module.RoomModule
import com.alarmapplication.di.module.ViewModelModule
import com.alarmapplication.domain.usecase.RemoveAlarmUseCase
import com.alarmapplication.presentation.acitivity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        AppModule::class,
        RoomModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: SetUpAlarmActivity)

    val removeAlarmUseCase: RemoveAlarmUseCase

    companion object {
        fun create(application: Application): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
        }
    }
}