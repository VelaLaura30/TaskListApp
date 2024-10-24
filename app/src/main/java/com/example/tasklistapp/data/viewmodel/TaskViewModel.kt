package com.example.tasklistapp.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasklistapp.data.model.Task

class TaskViewModel: ViewModel() {
    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val tasks: LiveData<MutableList<Task>> get() = _tasks

    private val _completedTasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val completedTasks: LiveData<MutableList<Task>> get() = _completedTasks

    fun addTask(task: Task) {
        val updatedTasks = _tasks.value ?: mutableListOf()
        updatedTasks.add(task)
        _tasks.value = updatedTasks
    }


    fun completeTask(task: Task) {
        val updatedTasks = _tasks.value ?: mutableListOf()
        val updatedCompletedTasks = _completedTasks.value ?: mutableListOf()

        updatedTasks.find { it.name == task.name }?.isCompleted = task.isCompleted

        if (task.isCompleted) {
            updatedCompletedTasks.add(task)
        }

        _tasks.value = updatedTasks
        _completedTasks.value = updatedCompletedTasks
    }

    private fun MutableLiveData<*>.notifyObservers() {
        this.value = this.value
    }
}