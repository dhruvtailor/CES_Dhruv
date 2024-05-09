package com.example.ces_dhruv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ces_dhruv.database.StudentDatabase
import com.example.ces_dhruv.databinding.ActivityStudentDetailsBinding
import com.example.ces_dhruv.ui.StudentListAdapter
import com.example.ces_dhruv.ui.StudentVIewModelFactory
import com.example.ces_dhruv.ui.StudentViewModel

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    private lateinit var studentListAdapter: StudentListAdapter
    private lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var database = StudentDatabase.getDatabase(this)
        val studentItemDao = database.studentItemDao()
        val factory = StudentVIewModelFactory(studentItemDao)
        viewModel = ViewModelProvider(this, factory)[StudentViewModel::class.java]

        setupUI()
    }

    private fun setupUI() {
        binding.rvStudentList.layoutManager = LinearLayoutManager(this)
        binding.rvStudentList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        // Initialize RecyclerView
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Initialize your RecyclerView and its adapter here
        // Observe the LiveData from the ViewModel to update the RecyclerView

        viewModel.studentList.observe(this) { studentItems ->
            // Update your adapter's data here
            studentListAdapter = StudentListAdapter(studentItems)
            binding.rvStudentList.adapter = studentListAdapter
            studentListAdapter.notifyDataSetChanged()
        }
    }
}