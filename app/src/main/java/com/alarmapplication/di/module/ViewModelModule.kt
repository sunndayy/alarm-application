package com.alarmapplication.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alarmapplication.common.ViewModelFactory
import com.alarmapplication.common.ViewModelKey
import com.alarmapplication.presentation.viewmodel.AlarmManagerViewModel
import com.alarmapplication.presentation.viewmodel.SetUpAlarmViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AlarmManagerViewModel::class)
    fun bindAlarmManagerViewModel(viewModel: AlarmManagerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SetUpAlarmViewModel::class)
    fun bindSetUpAlarmViewModel(viewModel: SetUpAlarmViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}