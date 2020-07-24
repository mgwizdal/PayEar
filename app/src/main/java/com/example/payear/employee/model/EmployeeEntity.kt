package com.example.payear.employee.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.payear.employee.model.EmployeeEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_EMPLOYEE_ID)
    val id: Int? = null,
    @ColumnInfo(name = COLUMN_EMPLOYEE_FIRST_NAME)
    val firstName: String,
    @ColumnInfo(name = COLUMN_EMPLOYEE_LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = COLUMN_EMPLOYEE_AGE)
    val age: Int,
    @ColumnInfo(name = COLUMN_EMPLOYEE_GENDER)
    val gender: Gender
) {
    companion object {
        const val TABLE_NAME = "employees"
        const val COLUMN_EMPLOYEE_ID = "id"
        const val COLUMN_EMPLOYEE_FIRST_NAME = "first_name"
        const val COLUMN_EMPLOYEE_LAST_NAME = "last_name"
        const val COLUMN_EMPLOYEE_GENDER = "gender"
        const val COLUMN_EMPLOYEE_AGE = "age"
    }
}