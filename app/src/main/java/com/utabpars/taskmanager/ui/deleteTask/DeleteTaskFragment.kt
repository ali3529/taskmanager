package com.utabpars.taskmanager.ui.deleteTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.utabpars.taskmanager.R
import com.utabpars.taskmanager.databinding.FragmentDeleteTaskBinding
import com.utabpars.taskmanager.db.TaskManagerDB
import com.utabpars.taskmanager.model.TaskModel
import com.utabpars.taskmanager.ui.showTask.NotifyTask
import com.utabpars.taskmanager.viewmodel.TaskViewModel
import com.utabpars.taskmanager.viewmodel.TaskViewModelFactory

/*
It's better to use private for constructor parameters and also
use val instead var for parameters. it's safer then var.
 */
class DeleteTaskFragment(
    private val taskModel: TaskModel,
    private val notifyTask: NotifyTask
) :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentDeleteTaskBinding
    lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //initialize ViewModel with ViewModelFactory and Pass the Arguments to it.
        viewModel =
            ViewModelProvider(
                this,
                TaskViewModelFactory(TaskManagerDB.getInctance(requireContext()).taskDao)
            ).get(TaskViewModel::class.java)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delete_task, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //delete task
        binding.deleteBtn.setOnClickListener {
            viewModel.deleteTaskFromDb(taskModel)
            //now you can dismiss this dialog.
            dismiss()
        }

    }

    //notify to update ui
    override fun dismiss() {
        super.dismiss()
        notifyTask.taskNotify()
    }

}