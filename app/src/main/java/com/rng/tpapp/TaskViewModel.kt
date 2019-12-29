package com.rng.tpapp

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import network.TasksRepository


class TaskViewModel : ViewModel(){

    private val repository = TasksRepository()

    private val tasks = mutableListOf<Task>()

    private fun deleteTask (task : Task){
        viewModelScope.launch {
            if(repository.deleteTask(task)){
                tasks.remove(task)
                taskAdapter.notifyDataSetChanged()
            }
        }

    }

    val taskAdapter = TasksAdapter(tasks) { task : Task -> deleteTask(task)}

    fun loadTask ()
    {
        viewModelScope.launch{
            val repo_tasks = repository.loadTasks()
            if(repo_tasks != null){
                tasks.clear()
                tasks.addAll(repo_tasks)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    fun createTask(task : Task)
    {
        viewModelScope.launch{
            if(repository.createTask(task)){
                tasks.add(task)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    fun editTask(task : Task){
        viewModelScope.launch{
            if(repository.editTask(task)){
                var updated_task = tasks.find{it.id == task.id}
                updated_task?.title = task.title
                updated_task?.description = task.description
                taskAdapter.notifyDataSetChanged()

            }
        }
    }
}