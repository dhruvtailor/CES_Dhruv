package com.example.ces_dhruv.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_list")
data class StudentItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val enrolledClass: String
)