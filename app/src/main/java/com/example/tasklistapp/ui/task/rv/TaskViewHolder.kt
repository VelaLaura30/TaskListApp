package com.example.tasklistapp.ui.task.rv

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapp.R
import com.example.tasklistapp.data.model.Task

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val checkBoxTask: CheckBox = itemView.findViewById(R.id.checkBoxTask)
    val textViewTaskName: TextView = itemView.findViewById(R.id.textViewTaskName)

    fun bind(task: Task, onTaskCompletedListener: (Task, Int) -> Unit) {
        textViewTaskName.text = task.name
        checkBoxTask.isChecked = task.isCompleted

        textViewTaskName.paintFlags = if (task.isCompleted) {
            textViewTaskName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textViewTaskName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        textViewTaskName.setOnClickListener {
            val bundle = Bundle().apply {
                putString("taskName", task.name)
            }
            it.findNavController().navigate(R.id.fragment_task_detail, bundle)
        }

        checkBoxTask.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            onTaskCompletedListener(task,adapterPosition)
        }
    }
}
