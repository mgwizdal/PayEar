package com.example.payear.employee.model.db

import androidx.room.Embedded
import androidx.room.Relation

class EmployeeWithAddressView {
    @Embedded
    lateinit var employeeEntity: EmployeeEntity

    @Relation(parentColumn = EmployeeEntity.COLUMN_EMPLOYEE_ID, entityColumn = AddressEntity.COLUMN_EMPLOYEE_ID, entity = AddressEntity::class)
    lateinit var addressList: List<AddressEntity>
}