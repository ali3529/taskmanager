package com.utabpars.taskmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.utabpars.taskmanager.db.TaskManagerDB
import com.utabpars.taskmanager.model.TaskModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DeleteTaskViewModel(application: Application) : AndroidViewModel(application){
    //database room
    val db= TaskManagerDB.getInctance(application)

    var deleteComplete= MutableLiveData<Int>()

    //coroutine
    val job= Job()
    val uiScope= CoroutineScope(Dispatchers.Main+job)


    fun deleteTask(taskModel: TaskModel){
        uiScope.launch {
           deleteComplete.value= db.taskDao.deletTask(taskModel)
        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
        db.close()
    }
}