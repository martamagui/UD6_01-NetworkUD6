package com.marta.ud6_01_networkud6.usescases.Tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.marta.ud6_01_networkud6.databinding.FragmentTaskListBinding
import com.marta.ud6_01_networkud6.model.TaskList
import com.marta.ud6_01_networkud6.usescases.common.TaskListAdapter


class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = TaskListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lista: MutableList<TaskList> = mutableListOf()
        lista.add(TaskList(1,"Lista gym",1))
        lista.add(TaskList(1,"Lista tareas clase",1))
        lista.add(TaskList(1,"Cosas de casa",1))
        adapter.submitList(lista)
        binding.rvTaskList.adapter = adapter
        binding.rvTaskList.layoutManager = LinearLayoutManager(context)

    }







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}