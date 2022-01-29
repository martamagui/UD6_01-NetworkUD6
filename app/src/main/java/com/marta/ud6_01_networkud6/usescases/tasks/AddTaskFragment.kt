package com.marta.ud6_01_networkud6.usescases.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marta.ud6_01_networkud6.R
import com.marta.ud6_01_networkud6.databinding.FragmentAddTaskBinding
import com.marta.ud6_01_networkud6.databinding.FragmentTaskListBinding


class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }
}