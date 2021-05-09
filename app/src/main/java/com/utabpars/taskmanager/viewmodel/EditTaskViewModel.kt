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

class EditTaskViewModel(application: Application) : AndroidViewModel(application){
    //db room
    val db= TaskManagerDB.getInctance(application)

    var editComplete=MutableLiveData<Int>()

    //coroutine
    val job= Job()
    val uiScope= CoroutineScope(Dispatchers.Main+job)

    fun editTask(taskModel: TaskModel){
        uiScope.launch {
            editComplete.value= db.taskDao.editTask(taskModel)

        }
    }

    //close coroutine and db
    override fun onCleared() {
        super.onCleared()
        job.cancel()
        db.close()
    }
}