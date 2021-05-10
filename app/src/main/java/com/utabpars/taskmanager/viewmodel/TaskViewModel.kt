package com.utabpars.taskmanager.viewmodel

import androidx.lifecycle.*
import com.utabpars.taskmanager.db.TaskManagerDAO
import com.utabpars.taskmanager.model.TaskModel
import kotlinx.coroutines.launch

//for viewModel please use Repository and give to viewModel.
class TaskViewModel(
    private val dao: TaskManagerDAO
) : ViewModel() {

    private val _tasks = MutableLiveData<List<TaskModel>>()
    val tasks: LiveData<List<TaskModel>> = _tasks

    init {
        fetchDataFromDb()
    }

    fun fetchDataFromDb() = viewModelScope.launch {
        _tasks.postValue(dao.getTasks())
    }

    /*
    viewModelScope Provide a Coroutine Scope for ViewModel and that Manage viewModel lifecycle
    it self. It's better to use this instead CoroutineScope with job...
     */
    fun insertOrUpdateTaskInDb(taskModel: TaskModel) = viewModelScope.launch {
        dao.insertOrUpdate(taskModel)
    }

    fun deleteTaskFromDb(taskModel: TaskModel) = viewModelScope.launch {
        dao.deletTask(taskModel)
    }

}