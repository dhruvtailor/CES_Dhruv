package com.example.ces_dhruv.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ces_dhruv.model.StudentItem
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentItemDao {
    @Insert
    suspend fun insert(item: StudentItem): Long

    @Query("SELECT * FROM student_list")
    fun getAllItems(): Flow<List<StudentItem>>
}