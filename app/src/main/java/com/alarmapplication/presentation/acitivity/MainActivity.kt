package com.alarmapplication.presentation.acitivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alarmapplication.AndroidApplication
import com.alarmapplication.common.AlarmUtils
import com.alarmapplication.databinding.ActivityMainBinding
import com.alarmapplication.presentation.adapter.AlarmsAdapter
import com.alarmapplication.presentation.listener.AlarmItemClickListener
import com.alarmapplication.presentation.uimodel.toAlarmUiModel
import com.alarmapplication.presentation.viewmodel.AlarmManagerViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val alarmManagerViewModel: AlarmManagerViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityMainBinding

    private val listener = object : AlarmItemClickListener {
        override fun onClick(index: Int) {
            val item = alarmsAdapter.getItems(index)?.toAlarmUiModel() ?: return
            alarmManagerViewModel.removeAlarm(item)
            alarmsAdapter.removeItem(index)
            AlarmUtils.cancelAlarm(this@MainActivity, item)
        }
    }

    private val alarmsAdapter: AlarmsAdapter = AlarmsAdapter(listener)

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        initViewBinding()
        initView()
        initViewModel()
    }

    override fun onStart() {
        super.onStart()
        getAlarms()
    }

    private fun inject() {
        (applicationContext as AndroidApplication).appComponent.inject(this)
    }

    private fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initView() {
        binding.btnAddAlarm.setOnClickListener { onAddAlarmClick() }
        binding.recyclerView.apply {
            adapter = alarmsAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
        }
    }

    private fun initViewModel() {
        alarmManagerViewModel.alarms.observe(this, {
            alarmsAdapter.setItems(it)
        })
    }

    private fun getAlarms() {
        alarmManagerViewModel.getListAlarms()
    }

    private fun onAddAlarmClick() {
        startActivity(Intent(this, SetUpAlarmActivity::class.java))
    }
}