package com.utabpars.taskmanager.ui.insertTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.utabpars.taskmanager.R
import com.utabpars.taskmanager.databinding.FragmentInsertTastBinding
import com.utabpars.taskmanager.db.TaskManagerDB
import com.utabpars.taskmanager.model.TaskModel
import com.utabpars.taskmanager.ui.showTask.NotifyTask
import com.utabpars.taskmanager.viewmodel.TaskViewModel
import com.utabpars.taskmanager.viewmodel.TaskViewModelFactory


class InsertTaskFragment(
    private val notifyTask: NotifyTask
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentInsertTastBinding
    lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // init layout whit data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert_tast, container, false)


        viewModel =
            ViewModelProvider(
                this,
                TaskViewModelFactory(TaskManagerDB.getInctance(requireContext()).taskDao)
            ).get(TaskViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //save task
        binding.saveTask.setOnClickListener {
            if (checkTitle()) {
                Snackbar.make(view, binding.title.text.toString(), Snackbar.LENGTH_SHORT).show()
                viewModel.insertOrUpdateTaskInDb(
                    TaskModel(
                        binding.title.text.toString(),
                        binding.discription.text.toString()
                    )
                )
                binding.title.setText("")
                binding.discription.setText("")
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