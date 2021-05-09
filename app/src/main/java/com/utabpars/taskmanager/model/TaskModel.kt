package com.utabpars.taskmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    @ColumnInfo(name = "task_title")
    var title:String,
    @ColumnInfo(name = "task_discription")
    var discription:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}