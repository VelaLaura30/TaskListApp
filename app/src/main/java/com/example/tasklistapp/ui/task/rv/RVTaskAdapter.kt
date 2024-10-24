package com.example.tasklistapp.ui.task.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapp.R
import com.example.tasklistapp.data.model.Task

class RVTaskAdapter(
        private val tasks: MutableList<Task>,
        private var onTaskCompletedListener: (Task, Int) -> Unit
    ) : RecyclerView.Adapter<TaskViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
            return TaskViewHolder(view)
        }

        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            val task = tasks[position]
            holder.bind(task) { updatedTask, adapterPosition ->
                tasks[adapterPosition] = updatedTask
                notifyItemChanged(adapterPosition)
            }
        }

    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tasks.size
}
