package com.utabpars.taskmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utabpars.taskmanager.model.TaskModel

@Database(entities = [TaskModel::class], version = 1, exportSchema = false)
abstract class TaskManagerDB : RoomDatabase() {

    abstract val taskDao: TaskManagerDAO

    companion object {
        var INCTANCE: TaskManagerDB? = null
        fun getInctance(context: Context): TaskManagerDB {
            var inctance = INCTANCE
            if (inctance == null) {
                inctance = Room.databaseBuilder(
                    context,
                    TaskManagerDB::class.java,
                    "taskManagerDB"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return inctance

        }
    }

}