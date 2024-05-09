package com.example.ces_dhruv

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ces_dhruv.database.StudentDatabase
import com.example.ces_dhruv.databinding.ActivityMainBinding
import com.example.ces_dhruv.model.StudentItem
import com.example.ces_dhruv.ui.StudentVIewModelFactory
import com.example.ces_dhruv.ui.StudentViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get the StudentItemDao instance from the database
        var database = StudentDatabase.getDatabase(this)
        val studentItemDao = database.studentItemDao()
        val factory = StudentVIewModelFactory(studentItemDao)
        viewModel = ViewModelProvider(this, factory)[StudentViewModel::class.java]

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnEnroll.setOnClickListener {
            val studentName = binding.etStudentName.text.toString()
            val classEnroll = binding.etClassEnroll.text.toString()
            // Perform checks and then insert item using the ViewModel
            if (studentName.isNotEmpty() && classEnroll.isNotEmpty()) {
                binding.tvErrorStudentName.visibility = View.GONE
                binding.tvErrorClassEnroll.visibility = View.GONE

                val studentItem = StudentItem(name = studentName, enrolledClass = classEnroll)
                viewModel.insertItem(studentItem)

                binding.etStudentName.text.clear()
                binding.etClassEnroll.text.clear()

                val snackbar = Snackbar.make(binding.root, "Student Enrolled Successfully...!!!", Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                if(studentName.isEmpty() && classEnroll.isNotEmpty()) {
                    binding.tvErrorStudentName.visibility = View.VISIBLE
                    binding.tvErrorClassEnroll.visibility = View.GONE
                }
                if(classEnroll.isEmpty() && studentName.isNotEmpty()) {
                    binding.tvErrorStudentName.visibility = View.GONE
                    binding.tvErrorClassEnroll.visibility = View.VISIBLE
                }
                if(classEnroll.isEmpty() && studentName.isEmpty()) {
                    binding.tvErrorStudentName.visibility = View.VISIBLE
                    binding.tvErrorClassEnroll.visibility = View.VISIBLE
                }
            }
        }

        binding.btnViewDetails.setOnClickListener {
            val intent = Intent(this@MainActivity, StudentDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}