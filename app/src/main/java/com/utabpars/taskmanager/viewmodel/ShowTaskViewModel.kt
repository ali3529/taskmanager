package com.utabpars.taskmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.utabpars.taskmanager.db.TaskManagerDB
import com.utabpars.taskmanager.model.TaskModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ShowTaskViewModel(application: Application) : AndroidViewModel(application) {
    //db room
    val db= TaskManagerDB.getInctance(application)
    var taskLiveData=MutableLiveData<List<TaskModel>>()

    //coroutine
    val job= Job()
    val uiScope= CoroutineScope(Dispatchers.Main+job)

    init {
        getTask()
    }
    fun getTask(){
        uiScope.launch {
            taskLiveData.value=db.taskDao.getTasks()

        }

    }
}