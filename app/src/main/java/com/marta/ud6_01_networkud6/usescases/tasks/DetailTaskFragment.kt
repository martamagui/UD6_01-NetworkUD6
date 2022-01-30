package com.marta.ud6_01_networkud6.usescases.tasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.marta.ud6_01_networkud6.R
import com.marta.ud6_01_networkud6.databinding.FragmentDetailTaskBinding
import com.marta.ud6_01_networkud6.databinding.FragmentTasksBinding
import com.marta.ud6_01_networkud6.model.Task
import com.marta.ud6_01_networkud6.provider.TaskApi
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class DetailTaskFragment : Fragment() {
    private var _binding: FragmentDetailTaskBinding? = null
    private val binding
        get() = _binding!!
    private val args: DetailTaskFragmentArgs by navArgs()
    private lateinit var task: Task
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestTaskById(args.taskId)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //Change to edit mode
    private fun changeToEditMode(){
    }
    private fun setTexts(task:Task){
        with(binding){
            etDetailTaskTitleContainer.visibility= View.INVISIBLE
            etDetailTaskDescriptionContainer.visibility = View.INVISIBLE
            tvDetailTaskTitle.text = task.title
            tvDetailDescription.text = task.description
            tvStatus.text = task.state

        }
    }
    //Request
    private fun requestTaskById(taskId: Int) {
        val service = TaskApi.service.getTaskById(taskId)
        val call = service.enqueue(object : Callback<Task>{
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                if (response.isSuccessful) {
                    task = response.body()!!
                    setTexts(task)
                } else {
                    Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Format faliure", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onFailure(call: Call<Task>, t: Throwable) {
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