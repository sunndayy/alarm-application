package com.alarmapplication.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.alarmapplication.AndroidApplication
import com.alarmapplication.common.AlarmUtils
import com.alarmapplication.common.NotificationUtils
import com.alarmapplication.presentation.uimodel.AlarmUiModel
import com.alarmapplication.presentation.uimodel.toEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val alarmUiModel = AlarmUiModel(
                id = intent?.getIntExtra(AlarmUtils.ALARM_ARG_ID, 0) ?: 0,
                hour = intent?.getIntExtra(AlarmUtils.ALARM_ARG_HOUR, 0) ?: 0,
                minute = intent?.getIntExtra(AlarmUtils.ALARM_ARG_MINUTE, 0) ?: 0,
                repeatDaily = intent?.getBooleanExtra(AlarmUtils.ALARM_ARG_REPEAT, false) ?: false,
            )
            NotificationUtils.showAlarmNotification(context, alarmUiModel)
            removeAlarmInDb(context, alarmUiModel)
        }
    }

    private fun removeAlarmInDb(context: Context, alarmUiModel: AlarmUiModel) {
        if (alarmUiModel.repeatDaily) {
            return
        }
        val removeAlarmUseCase = (context.applicationContext as AndroidApplication).appComponent.removeAlarmUseCase
        GlobalScope.launch {
            removeAlarmUseCase(alarmUiModel.toEntity())
        }
    }
}