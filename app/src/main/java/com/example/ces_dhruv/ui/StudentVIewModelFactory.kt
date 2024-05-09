package com.example.ces_dhruv.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ces_dhruv.dao.StudentItemDao

class StudentVIewModelFactory(private val studentItemDao: StudentItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(studentItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}