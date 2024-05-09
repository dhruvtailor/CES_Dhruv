package com.example.ces_dhruv.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ces_dhruv.dao.StudentItemDao
import com.example.ces_dhruv.model.StudentItem
import kotlinx.coroutines.launch

class StudentViewModel(private val studentItemDao: StudentItemDao) : ViewModel() {

    //Convert Flow to LiveData
    val studentList: LiveData<List<StudentItem>> = studentItemDao.getAllItems().asLiveData()

    fun insertItem(studentItem: StudentItem) {
        viewModelScope.launch {
            studentItemDao.insert(studentItem)
        }
    }
}