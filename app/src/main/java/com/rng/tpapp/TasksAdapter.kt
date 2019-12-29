package com.rng.tpapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter(private val tasks: List<Task>, private val onDeleteClickListener : (Task)->Unit) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>(){


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false), onDeleteClickListener)
    }

    override fun getItemCount(): Int {
        return tasks.count()
    }

    class TaskViewHolder(itemView: View, val delete : (Task)->Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            itemView.task_title.text = task.title + ": "
            itemView.task_description.text = task.description

            itemView.task_delet_button.setOnClickListener{delete(task)}
            itemView.task_edit_button.setOnClickListener{edit(task)}

        }
        private fun edit(task : Task){
            val intent = Intent(itemView.context,TaskFormActivity::class.java)
            intent.putExtra("id",task.id)
            intent.putExtra("title",task.title)
            intent.putExtra("description",task.description)
            itemView.context.startActivity(intent)
        }

    }

}