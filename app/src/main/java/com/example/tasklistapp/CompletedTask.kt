package com.example.tasklistapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapp.data.viewmodel.TaskViewModel
import com.example.tasklistapp.ui.completedtask.rv.RVCompletedTaskAdapter
import com.example.tasklistapp.ui.task.rv.RVTaskAdapter

class CompletedTask : Fragment(R.layout.completed_tasks) {

    private val taskViewModel: TaskViewModel by activityViewModels()
    private lateinit var recyclerViewCompletedTasks: RecyclerView
    private lateinit var taskAdapter: RVTaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCompletedTasks = view.findViewById(R.id.recyclerViewCompletedTasks)

        taskAdapter = RVTaskAdapter(mutableListOf()) { task, position ->
            taskViewModel.completeTask(task)
            taskAdapter.notifyItemChanged(position)
        }
        recyclerViewCompletedTasks.adapter = taskAdapter
        recyclerViewCompletedTasks.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            val completedTasks = tasks.filter { it.isCompleted }
            taskAdapter.updateTasks(completedTasks)
        }

    }
}