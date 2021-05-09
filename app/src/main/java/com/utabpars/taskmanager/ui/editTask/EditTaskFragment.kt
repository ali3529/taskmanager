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
import com.utabpars.taskmanager.model.TaskModel
import com.utabpars.taskmanager.ui.showTask.NotifyTask
import com.utabpars.taskmanager.viewmodel.EditTaskViewModel


class EditTaskFragment(var taskModel: TaskModel,val notifyTask: NotifyTask) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentEditTaskBinding
    lateinit var viewModel:EditTaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel=ViewModelProvider(this).get(EditTaskViewModel::class.java)
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_edit_task,container,false)
        //set old task to ui for edit
        binding.taskmodel=taskModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //save edit
        binding.editTask.setOnClickListener{edit->
            if (checkTitle()){
                taskModel.title=binding.title.text.toString()
                taskModel.discription= binding.discription.text.toString()
                viewModel.editTask(taskModel)


            }else{
                Snackbar.make(view,"لطفا عنوان را وارد کنید", Snackbar.LENGTH_SHORT).show()
            }
        }
        //edit callback
        viewModel.editComplete.observe(viewLifecycleOwner,{complete->
            dismiss()
        })
    }
    //check title
    private fun checkTitle():Boolean{
        return binding.title.text.toString().isNotEmpty()
    }

    //notify to update ui
    override fun dismiss() {
        super.dismiss()
        notifyTask.taskNotify()
    }
}