package com.example.tasklistapp.ui.completedtask.rv

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapp.R
import com.example.tasklistapp.data.model.Task

class CompletedTaskViewHolder (
    itemView: View
): RecyclerView.ViewHolder(itemView) {
    val checkBoxTask: CheckBox = itemView.findViewById(R.id.checkBoxTask)
    val textViewTaskName: TextView = itemView.findViewById(R.id.textViewTaskName)

    fun bind(task: Task) {
        textViewTaskName.text = task.name
        checkBoxTask.isChecked = task.isCompleted
        checkBoxTask.isEnabled = false
        textViewTaskName.paintFlags = textViewTaskName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}