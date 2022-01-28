package com.marta.ud6_01_networkud6.usescases.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marta.ud6_01_networkud6.databinding.ItemTaskBinding
import com.marta.ud6_01_networkud6.databinding.ItemTasklistBinding
import com.marta.ud6_01_networkud6.model.Task
import com.marta.ud6_01_networkud6.model.TaskList
import com.marta.ud6_01_networkud6.usescases.common.TaskListAdapter.ViewHolder



class TaskAdapter: ListAdapter<Task, ViewHolder>(TaskItemCallBack()) {
    inner class ViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TaskListAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
class TaskItemCallBack: DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }
}