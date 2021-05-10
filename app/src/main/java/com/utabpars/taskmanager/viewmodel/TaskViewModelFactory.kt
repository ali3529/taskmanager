package com.utabpars.taskmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utabpars.taskmanager.db.TaskManagerDAO

class TaskViewModelFactory(
    private val dao: TaskManagerDAO
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(dao) as T
    }
}