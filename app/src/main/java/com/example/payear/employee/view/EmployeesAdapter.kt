package com.example.payear.employee.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.payear.R
import com.example.payear.employee.model.EmployeeItem
import com.example.payear.utils.DiffCallback
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeesAdapter() :
    RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {
    private var employeeItemList: MutableList<EmployeeItem> = mutableListOf()
    var onClickListener: ((Int, String) -> Unit)? = null



    fun replaceData(newList: List<EmployeeItem>) {
        employeeItemList.clear()
        employeeItemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int = employeeItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employeeItemList[position])
    }

    override fun getItemViewType(position: Int) = R.layout.item_employee

    class ViewHolder(
        itemView: View,
        private val onClickListener: ((Int, String) -> Unit)? = null
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: EmployeeItem) {
            val fullName = "${item.lastName} ${item.firstName}"
            itemView.namesTextView.text = fullName
            itemView.ageTextView.text = item.age.toString()
            itemView.genderTextView.text = item.gender.toString()
            itemView.setOnClickListener { item.id?.let{ onClickListener?.invoke(it, fullName) } }
        }
    }
}
