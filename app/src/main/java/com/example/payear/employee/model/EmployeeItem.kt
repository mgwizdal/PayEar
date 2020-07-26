package com.example.payear.employee.model

data class EmployeeItem(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Gender,
    val addressList: List<AddressItem>
)