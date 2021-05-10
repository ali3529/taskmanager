package com.utabpars.taskmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//for entities set tableName and don't use ClassName for (TableName).
@Entity(tableName = "tbl_task")
data class TaskModel(
    @ColumnInfo(name = "task_title")
    var title: String,
    @ColumnInfo(name = "task_description")
    var discription: String
) {
    @PrimaryKey(autoGenerate = true)
    //It's better to define this variable nullable and set null initially
    var id: Int? = null
}