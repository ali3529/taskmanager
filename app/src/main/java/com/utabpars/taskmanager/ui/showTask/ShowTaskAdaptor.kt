package com.utabpars.taskmanager.ui.showTask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.utabpars.taskmanager.R
import com.utabpars.taskmanager.databinding.TaskItemViewBinding
import com.utabpars.taskmanager.model.TaskModel
import com.utabpars.taskmanager.ui.deleteTask.DeleteCallback
import com.utabpars.taskmanager.ui.editTask.EditTastCallback

class  ShowTaskAdaptor(var list: List<TaskModel>,
                       val editTastCallback: EditTastCallback,
                       val deleteCallback: DeleteCallback)
    : RecyclerView.Adapter<ShowTaskAdaptor.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding:TaskItemViewBinding =DataBindingUtil.inflate(inflater, R.layout.task_item_view,parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.taskmodel=list.get(position)
        //edit button
        holder.binding.itemEdit.setOnClickListener{o->
            editTastCallback.EditTask(list.get(position))
        }
        //delete button
        holder.binding.itemDelete.setOnClickListener{
                delete-> deleteCallback.onDeleteClicked(list.get(position))
        }
    }

    override fun getItemCount(): Int =   list.size



    class TaskViewHolder(val binding: TaskItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}