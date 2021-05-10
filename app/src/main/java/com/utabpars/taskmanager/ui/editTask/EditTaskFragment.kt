package com.utabpars.taskmanager.ui.editTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.utabpars.taskmanager.R
import com.utabpars.taskmanager.databinding.FragmentEditTaskBinding
import com.utabpars.taskmanager.db.TaskManagerDB
import com.utabpars.taskmanager.model.TaskModel
import com.utabpars.taskmanager.ui.showTask.NotifyTask
import com.utabpars.taskmanager.viewmodel.TaskViewModel
import com.utabpars.taskmanager.viewmodel.TaskViewModelFactory


class EditTaskFragment(
    private val taskModel: TaskModel,
    private val notifyTask: NotifyTask
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentEditTaskBinding
    lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(
                this,
                TaskViewModelFactory(TaskManagerDB.getInctance(requireContext()).taskDao)
            ).get(TaskViewModel::class.java)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_task, container, false)
        //set old task to ui for edit
        binding.taskmodel = taskModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //save edit
        binding.editTask.setOnClickListener { edit ->
            if (checkTitle()) {
                taskModel.title = binding.title.text.toString()
                taskModel.discription = binding.discription.text.toString()
                viewModel.insertOrUpdateTaskInDb(taskModel)
                dismiss()
            } else {
                Snackbar.make(view, "لطفا عنوان را وارد کنید", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    //check title
    private fun checkTitle(): Boolean {
        return binding.title.text.toString().isNotEmpty()
    }

    //notify to update ui
    override fun dismiss() {
        super.dismiss()
        notifyTask.taskNotify()
    }
}