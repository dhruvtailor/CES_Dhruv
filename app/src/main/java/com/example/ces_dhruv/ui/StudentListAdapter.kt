package com.example.ces_dhruv.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ces_dhruv.R
import com.example.ces_dhruv.model.StudentItem

class StudentListAdapter (
    private var items: List<StudentItem>
    ) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

        class StudentViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.student_item_layout, parent, false)) {
            private var tvStudentName: TextView? = null
            private var tvEnrolledClass: TextView? = null

            init {
                tvStudentName = itemView.findViewById(R.id.tvStudentName)
                tvEnrolledClass = itemView.findViewById(R.id.tvEnrolledClass)
            }

            fun bind(studentItem: StudentItem) {
                tvStudentName?.text = studentItem.name
                tvEnrolledClass?.text = studentItem.enrolledClass
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return StudentViewHolder(inflater, parent)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            val studentItem: StudentItem = items[position]
            holder.bind(studentItem)
        }

        override fun getItemCount(): Int = items.size
}