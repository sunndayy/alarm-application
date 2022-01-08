package com.alarmapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alarmapplication.R
import com.alarmapplication.presentation.listener.AlarmItemClickListener
import com.alarmapplication.presentation.uimodel.AlarmListItemModel
import com.alarmapplication.presentation.uimodel.AlarmUiModel
import com.alarmapplication.presentation.uimodel.getAlarmListItemModel

class AlarmsAdapter(
    private val listener: AlarmItemClickListener
) : RecyclerView.Adapter<AlarmsAdapter.AlarmViewHolder>() {

    private val items: MutableList<AlarmListItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlarmViewHolder(
            layoutInflater.inflate(R.layout.layout_item_alarm, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<AlarmUiModel>) {
        val list = items.map { getAlarmListItemModel(it) }
        this.items.clear()
        this.items.addAll(list)
        notifyItemRangeChanged(0, this.items.size)
    }

    fun removeItem(index: Int) {
        if (index < 0 || index >= items.size) {
            return
        }
        this.items.removeAt(index)
        notifyItemChanged(index)
    }

    fun getItems(index: Int): AlarmListItemModel? {
        if (index < 0 || index >= items.size) {
            return null
        }
        return items[index]
    }

    class AlarmViewHolder(itemView: View, private val listener: AlarmItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(item: AlarmListItemModel) {
            itemView.findViewById<TextView>(R.id.tvAlarmTime).text = item.time
            itemView.findViewById<TextView>(R.id.tvRepeatMode).text = item.repeatDaily
            itemView.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }
    }
}