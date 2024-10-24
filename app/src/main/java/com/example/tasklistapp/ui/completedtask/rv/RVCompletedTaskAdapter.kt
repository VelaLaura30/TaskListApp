package com.example.tasklistapp.ui.completedtask.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapp.R
import com.example.tasklistapp.data.model.Task

class RVCompletedTaskAdapter(
    private val completedTasks: MutableList<Task>
) : RecyclerView.Adapter<CompletedTaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return CompletedTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompletedTaskViewHolder, position: Int) {
        val task = completedTasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = completedTasks.size

}
