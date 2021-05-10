package com.utabpars.taskmanager.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.utabpars.taskmanager.model.TaskModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskManagerDAO {

    /*
    also you can use insert and update in one function
    Using the (onConflict) Method provide a way to Insert and Update in a one Hand.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(taskModel: TaskModel)

    @Query("SELECT * FROM tbl_task")
    suspend fun getTasks(): List<TaskModel>

    @Delete
    suspend fun deletTask(taskModel: TaskModel)
}