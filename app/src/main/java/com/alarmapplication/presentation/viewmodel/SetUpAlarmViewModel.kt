package com.alarmapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alarmapplication.domain.usecase.AddAlarmUseCase
import com.alarmapplication.presentation.uimodel.AlarmUiModel
import com.alarmapplication.presentation.uimodel.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SetUpAlarmViewModel @Inject constructor(
    private val addAlarmUseCase: AddAlarmUseCase
) : ViewModel() {

    fun addAlarm(alarmUiModel: AlarmUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addAlarmUseCase(alarmUiModel.toEntity())
        }
    }
}