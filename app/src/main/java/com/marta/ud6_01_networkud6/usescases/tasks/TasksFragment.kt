package com.marta.ud6_01_networkud6.usescases.tasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.marta.ud6_01_networkud6.databinding.FragmentTasksBinding
import com.marta.ud6_01_networkud6.model.Task
import com.marta.ud6_01_networkud6.provider.TaskApi
import com.marta.ud6_01_networkud6.provider.TaskApiService
import com.marta.ud6_01_networkud6.usescases.common.TaskAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksFragment : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = TaskAdapter()
    private val args: TasksFragmentArgs by navArgs()
    private var taskList: MutableList<Task> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = LinearLayoutManager(context)
        val listId: Int = args.listIdFk
        requestTask(listId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Request
    private fun requestTask(listId: Int) {
        val service = TaskApi.service.getTaskByListId(listId)
        val call = service.enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                if (response.isSuccessful) {
                    response.body()?.let { taskList.addAll(it) }
                    adapter.submitList(taskList)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Format faliure", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "FALIURE(╯°□°）╯︵ ┻━┻ Connection faliure ",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("faliure", "$t")
            }
        })
    }
}