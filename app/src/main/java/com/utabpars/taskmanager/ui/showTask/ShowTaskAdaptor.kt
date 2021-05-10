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

/*
please don't pass list in constructor ://
create a function and use them everyWhere you need.
 */
class ShowTaskAdaptor(
    private val editTastCallback: EditTastCallback,
    private val deleteCallback: DeleteCallback
) : RecyclerView.Adapter<ShowTaskAdaptor.TaskViewHolder>() {

    //create a MutableList and use them in RecyclerView
    private val list = mutableListOf<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: TaskItemViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.task_item_view, parent, false)
        return TaskViewHolder(binding)
    }

    /*
    Suggestion:
    also you can create a variable for all of items in (task_item_view) and use findViewById
    for initialize them in a function in TaskViewHolder.
     */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.taskmodel = list[position]
        //edit button
        holder.binding.itemEdit.setOnClickListener { o ->
            editTastCallback.EditTask(list[position])
        }
        //delete button
        holder.binding.itemDelete.setOnClickListener { delete ->
            deleteCallback.onDeleteClicked(list[position])
        }
    }

    /*
    this function add all items to adapter list and then show them in recyclerView.
     */
    fun addAll(tasks: List<TaskModel>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size


    class TaskViewHolder(val binding: TaskItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}