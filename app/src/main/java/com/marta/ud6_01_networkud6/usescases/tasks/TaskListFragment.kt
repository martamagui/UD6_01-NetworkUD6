package com.marta.ud6_01_networkud6.usescases.tasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marta.ud6_01_networkud6.databinding.FragmentTaskListBinding
import com.marta.ud6_01_networkud6.model.TaskList
import com.marta.ud6_01_networkud6.provider.TaskApi
import com.marta.ud6_01_networkud6.usescases.common.TaskListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding
        get() = _binding!!
    private val lista: MutableList<TaskList> = mutableListOf()
    private val adapter = TaskListAdapter{
        viewChange(it.listId)
    }


    //TODO crear una función que compruebe los ids de las listas antes de guardarlas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestTaskList()
        binding.rvTaskList.adapter = adapter
        binding.rvTaskList.layoutManager = LinearLayoutManager(context)

        binding.fabAddList.setOnClickListener {
            val text = binding.tfNewList.text.toString()
            addList(text)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //Req data
    private fun requestTaskList(){
        val service = TaskApi.service.getTaskLists()
        val call = service.enqueue(object : Callback<List<TaskList>>{
            override fun onResponse(
                call: Call<List<TaskList>>,
                response: Response<List<TaskList>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { lista.addAll(it) }
                    adapter.submitList(lista)
                    adapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(context, "RESPONSE(╯°□°）╯︵ ┻━┻ Connection faliure", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<TaskList>>, t: Throwable) {
                Toast.makeText(context, "FALIURE(╯°□°）╯︵ ┻━┻ Connection faliure ", Toast.LENGTH_SHORT).show()
                Log.e("faliure","$t")
            }

        })
    }
    //Add
    private fun addList(title: String){
        val newList: TaskList  = TaskList((lista.size+1), title, 1)
        val service = TaskApi.service.addList(newList)
        val call = service.enqueue(object : Callback<Any>{
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.isSuccessful){
                    lista.add(newList)
                    adapter.submitList(lista)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Format faliure", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Connection faliure ", Toast.LENGTH_SHORT).show()
                Log.e("faliure","$t")
            }
        })
    }
    private fun viewChange(userId: Int){
        val action = TaskListFragmentDirections.actionTaskListFragmentToTasksFragment(userId)
        findNavController().navigate(action)
    }
}