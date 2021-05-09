package com.utabpars.taskmanager.ui.editTask

import com.utabpars.taskmanager.model.TaskModel

interface EditTastCallback{
    fun EditTask(taskModel: TaskModel)
}