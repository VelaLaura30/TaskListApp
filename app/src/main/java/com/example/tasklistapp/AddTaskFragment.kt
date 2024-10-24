package com.example.tasklistapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapp.data.model.Task
import com.example.tasklistapp.data.viewmodel.TaskViewModel
import com.example.tasklistapp.ui.task.rv.RVTaskAdapter

class AddTaskFragment: Fragment(R.layout.add_task) {

    private val taskViewModel: TaskViewModel by activityViewModels()
    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var recyclerViewTasks: RecyclerView
    private lateinit var taskAdapter: RVTaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonTaskCompleted: Button = view.findViewById(R.id.buttonTaskCompleted)
        editTextTask = view.findViewById(R.id.editTextTask)
        buttonAddTask = view.findViewById(R.id.buttonAddTask)
        recyclerViewTasks = view.findViewById(R.id.recyclerViewTasks)


        taskAdapter = RVTaskAdapter(taskViewModel.tasks.value ?: mutableListOf()) { task, position ->
            taskViewModel.completeTask(task)
            taskAdapter.notifyItemChanged(position)
        }

        recyclerViewTasks.adapter = taskAdapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(requireContext())

        buttonAddTask.setOnClickListener {
            val taskName = editTextTask.text.toString()
            if (taskName.isNotEmpty()) {
                val task = Task(taskName)
                taskViewModel.addTask(task)
                editTextTask.text.clear()
            }
        }

        buttonTaskCompleted.setOnClickListener {
            findNavController().navigate(R.id.action_addTask_to_completedTasks)
        }

        taskViewModel.tasks.observe(viewLifecycleOwner){
            taskAdapter.notifyDataSetChanged()
        }

    }
}
