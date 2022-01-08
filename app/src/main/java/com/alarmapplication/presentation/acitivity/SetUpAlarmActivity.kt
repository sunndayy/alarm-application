package com.alarmapplication.presentation.acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.alarmapplication.AndroidApplication
import com.alarmapplication.common.AlarmUtils
import com.alarmapplication.common.TimePickerUtils
import com.alarmapplication.common.ToastUtils
import com.alarmapplication.databinding.ActivitySetUpAlarmBinding
import com.alarmapplication.presentation.uimodel.AlarmUiModel
import com.alarmapplication.presentation.viewmodel.SetUpAlarmViewModel
import javax.inject.Inject
import kotlin.random.Random

class SetUpAlarmActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val setUpAlarmViewModel: SetUpAlarmViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivitySetUpAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        initViewBinding()
        initView()
    }

    private fun inject() {
        (applicationContext as AndroidApplication).appComponent.inject(this)
    }

    private fun initViewBinding() {
        binding = ActivitySetUpAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initView() {
        binding.btnAddAlarm.setOnClickListener { onAddAlarmClick() }
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun onAddAlarmClick() {
        val alarmUIModel = AlarmUiModel(
            id = Random.nextInt(Int.MAX_VALUE),
            hour = TimePickerUtils.getHour(binding.timePicker),
            minute = TimePickerUtils.getMinute(binding.timePicker),
            repeatDaily = binding.checkbox.isChecked
        )
        setUpAlarmViewModel.addAlarm(alarmUIModel)
        AlarmUtils.scheduleAlarm(this, alarmUIModel)
        ToastUtils.showToast(
            this,
            String.format("Set up alarm at ${alarmUIModel.hour}:${alarmUIModel.minute}")
        )
        finish()
    }
}