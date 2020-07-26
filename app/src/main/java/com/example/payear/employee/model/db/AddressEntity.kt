package com.example.payear.employee.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.payear.employee.model.db.AddressEntity.Companion.COLUMN_EMPLOYEE_ID
import com.example.payear.employee.model.db.AddressEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = EmployeeEntity::class,
        parentColumns = [EmployeeEntity.COLUMN_EMPLOYEE_ID],
        childColumns = [COLUMN_EMPLOYEE_ID],
        onDelete = ForeignKey.CASCADE
    )])
class AddressEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ADDRESS_ID)
    val id: Int? = null,
    @ColumnInfo(name = COLUMN_EMPLOYEE_ID)
    val employeeId: Int,
    @ColumnInfo(name = COLUMN_ADDRESS)
    val address: String
) {
    companion object {
        const val TABLE_NAME = "address"
        const val COLUMN_ADDRESS_ID = "id_address"
        const val COLUMN_EMPLOYEE_ID = "employee_id"
        const val COLUMN_ADDRESS = "address"
    }
}