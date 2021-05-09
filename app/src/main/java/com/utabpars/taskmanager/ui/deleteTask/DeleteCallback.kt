package com.utabpars.taskmanager.ui.deleteTask

import com.utabpars.taskmanager.model.TaskModel

interface  DeleteCallback{
    fun onDeleteClicked(taskModel: TaskModel);
}