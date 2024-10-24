package com.example.tasklistapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class TaskDetailFragment : Fragment(R.layout.task_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskName = arguments?.getString("taskName")

        view.findViewById<TextView>(R.id.textViewTaskDetail).text = taskName
    }
}