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
import com.utabpars.taskmanager.model.TaskModel
import com.utabpars.taskmanager.ui.showTask.NotifyTask
import com.utabpars.taskmanager.viewmodel.DeleteTaskViewModel

class DeleteTaskFragment(var taskModel: TaskModel, val notifyTask: NotifyTask) : BottomSheetDialogFragment() {
    lateinit var binding:FragmentDeleteTaskBinding
    lateinit var viewModel: DeleteTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel= ViewModelProvider(this).get(DeleteTaskViewModel::class.java)
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_delete_task,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //delete task
        binding.deleteBtn.setOnClickListener{
            viewModel.deleteTask(taskModel)
        }
        //delete task callback
        viewModel.deleteComplete.observe(viewLifecycleOwner,{delete_complete->
            dismiss()
        })
    }
    //notify to update ui
    override fun dismiss() {
        super.dismiss()
        notifyTask.taskNotify()
    }

}