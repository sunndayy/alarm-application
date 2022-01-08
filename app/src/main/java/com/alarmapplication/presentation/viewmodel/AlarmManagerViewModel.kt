package com.alarmapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alarmapplication.domain.usecase.GetListAlarmUseCase
import com.alarmapplication.domain.usecase.RemoveAlarmUseCase
import com.alarmapplication.presentation.uimodel.AlarmUiModel
import com.alarmapplication.presentation.uimodel.getAlarmUiModel
import com.alarmapplication.presentation.uimodel.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlarmManagerViewModel @Inject constructor(
    private val getListAlarmUseCase: GetListAlarmUseCase,
    private val removeAlarmUseCase: RemoveAlarmUseCase
) : ViewModel() {

    private val _alarms: MutableLiveData<List<AlarmUiModel>> = MutableLiveData()
    val alarms: LiveData<List<AlarmUiModel>> = _alarms

    fun getListAlarms() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getListAlarmUseCase().map { getAlarmUiModel(it) }
            _alarms.postValue(result)
        }
    }

    fun removeAlarm(alarmUiModel: AlarmUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            removeAlarmUseCase(alarmUiModel.toEntity())
        }
    }
}