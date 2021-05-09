package com.utabpars.taskmanager.ui.showTask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.utabpars.taskmanager.R
import com.utabpars.taskmanager.databinding.FragmentTastBinding
import com.utabpars.taskmanager.model.TaskModel
import com.utabpars.taskmanager.ui.deleteTask.DeleteCallback
import com.utabpars.taskmanager.ui.deleteTask.DeleteTaskFragment
import com.utabpars.taskmanager.ui.editTask.EditTaskFragment
import com.utabpars.taskmanager.ui.editTask.EditTastCallback
import com.utabpars.taskmanager.ui.insertTask.InsertTaskFragment
import com.utabpars.taskmanager.viewmodel.ShowTaskViewModel


class TastFragment : Fragment() {

    lateinit var binding: FragmentTastBinding
    private lateinit var insertTaskFragment:InsertTaskFragment
    private lateinit var editTaskFragment:EditTaskFragment
    private lateinit var deleteTaskFragment: DeleteTaskFragment
    lateinit var viewModel: ShowTaskViewModel
    lateinit var adaptor: ShowTaskAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_tast,container,false)
        viewModel= ViewModelProvider(this).get(ShowTaskViewModel::class.java)
        insertTaskFragment= InsertTaskFragment(notifyTask)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //for add new task
        binding.addTask.setOnClickListener { addtask ->
            insertTaskFragment.show(activity!!.supportFragmentManager,"insert task")
        }

        //show task fetch from db
        viewModel.taskLiveData.observe(viewLifecycleOwner, { tasks ->

            //init recyclerview
            binding.recyclerview.apply {
                layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                hasFixedSize()
                adaptor=ShowTaskAdaptor(tasks,editTastCallback,deleteCallback)
                adapter =adaptor
            }
        })
    }
    //edit task
    var editTastCallback= object : EditTastCallback {
        override fun EditTask(taskModel: TaskModel) {
            editTaskFragment= EditTaskFragment(taskModel,notifyTask)
            editTaskFragment.show(activity!!.supportFragmentManager,"edit task")
        }
    }
    //delete task
    var deleteCallback=object :DeleteCallback{
        override fun onDeleteClicked(taskModel: TaskModel) {
            deleteTaskFragment= DeleteTaskFragment(taskModel,notifyTask)
            deleteTaskFragment.show(activity!!.supportFragmentManager,"delete task")

        }

    }

    //update ui
    val notifyTask=object : NotifyTask {
        override fun taskNotify() {
            viewModel.getTask()
            adaptor.notifyDataSetChanged()
        }

    }

}