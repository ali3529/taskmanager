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

class InsertTaskViewModel(application: Application) : AndroidViewModel(application){
    //coroutine
    private val taskJob= Job();
    private val uiScope= CoroutineScope(Dispatchers.Main+taskJob)
    private val db= TaskManagerDB.getInctance(application)
    var taskComplete=MutableLiveData<Long>()



    fun insertTask(taskModel: TaskModel){
        uiScope.launch {
            taskComplete.value=db.taskDao.saveTask(taskModel)
        }
    }


    //close coroutine and db
    override fun onCleared() {
        super.onCleared()
        taskJob.cancel()
        db.close()
    }
}