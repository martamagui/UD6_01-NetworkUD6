package com.marta.ud6_01_networkud6.usescases.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.marta.ud6_01_networkud6.databinding.ItemTaskBinding
import com.marta.ud6_01_networkud6.databinding.ItemTasklistBinding
import com.marta.ud6_01_networkud6.model.Task
import com.marta.ud6_01_networkud6.model.TaskList
import com.marta.ud6_01_networkud6.usescases.common.TaskListAdapter.ViewHolder
import androidx.recyclerview.widget.ListAdapter


class TaskAdapter: ListAdapter<Task, TaskAdapter.ViewHolderTask>(TaskItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolderTask {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTaskBinding  = ItemTaskBinding.inflate(inflater, parent, false)
        return ViewHolderTask(binding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolderTask, position: Int) {
        val task = getItem(position)
        holder.binding.tvTask.text = task.title
    }
    inner class ViewHolderTask( val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root)


}
class TaskItemCallBack: DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }
}