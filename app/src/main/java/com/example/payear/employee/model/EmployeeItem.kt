package com.example.payear.employee.model

import com.example.payear.utils.DiffCallbackItem

data class EmployeeItem(
    override val id: Int?,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Gender
): DiffCallbackItem