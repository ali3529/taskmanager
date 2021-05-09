package com.utabpars.taskmanager.db

import androidx.room.*
import com.utabpars.taskmanager.model.TaskModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskManagerDAO{
    @Insert
    suspend fun saveTask(taskModel: TaskModel):Long

    @Query("SELECT * FROM TaskModel")
    suspend fun getTasks():List<TaskModel>

    @Update
    suspend fun editTask(taskModel: TaskModel):Int

    @Delete
    suspend fun deletTask(taskModel: TaskModel):Int
}