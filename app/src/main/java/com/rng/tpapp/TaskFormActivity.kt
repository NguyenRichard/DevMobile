package com.rng.tpapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_task_form.*

class TaskFormActivity : AppCompatActivity() {

    private val taskViewModel by lazy {
        ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        task_title_create_input.setText(intent.getStringExtra("title")?:"")
        task_description_create_input.setText(intent.getStringExtra("description")?:"")

        val id = intent.getStringExtra("id")

        if(id == null){

            button_createTask.setOnClickListener{createTask()}
        }
        else{
            button_createTask.text = "Save"
            button_createTask.setOnClickListener{editTask(id)}
        }
        button_back.setOnClickListener{backToMain()}
    }

    private fun createTask(){
        var title = task_title_create_input.text.toString()
        var description = task_description_create_input.text.toString()
        if(title != "" && description != ""){
            var id = ""+title.hashCode()+description.hashCode()
            val newTask = Task(id, title, description)
            taskViewModel.createTask(newTask)
        }

        backToMain()
    }

    private fun editTask(id : String){
        var title = task_title_create_input.text.toString()
        var description = task_description_create_input.text.toString()
        if(title != "" && description != ""){
            val newTask = Task(id, title, description)
            taskViewModel.editTask(newTask)
        }

        backToMain()
    }

    private fun backToMain(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
