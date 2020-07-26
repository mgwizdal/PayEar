package com.example.payear.employee.model.db

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(vararg employeeEntities: EmployeeEntity): Array<Long>

    @Query(GET_ALL_ITEMS)
    fun getEmployees(): Flowable<List<EmployeeEntity>>

    @Query(GET_SINGLE)
    fun getEmployeeById(id: Int): Flowable<EmployeeEntity>

    @Query(DELETE_ALL)
    fun deleteById(id: Int)

    @Query(UPDATE_BY_ID)
    fun updateEmployee(id: Int, firstName: String, lastName: String, age: Int, gender: String)

    @Query(GET_ALL_ITEMS)
    fun getEmployeesView(): Flowable<List<EmployeeWithAddressView>>

    companion object {
        private const val GET_ALL_ITEMS = "SELECT * FROM ${EmployeeEntity.TABLE_NAME}"
        private const val GET_SINGLE =
            "SELECT * FROM ${EmployeeEntity.TABLE_NAME} WHERE ${EmployeeEntity.COLUMN_EMPLOYEE_ID} = :id"
        private const val DELETE_ALL =
            "DELETE FROM ${EmployeeEntity.TABLE_NAME} WHERE ${EmployeeEntity.COLUMN_EMPLOYEE_ID} = :id"
        private const val UPDATE_BY_ID = "UPDATE ${EmployeeEntity.TABLE_NAME} SET " +
                "${EmployeeEntity.COLUMN_EMPLOYEE_FIRST_NAME} =  :firstName, " +
                "${EmployeeEntity.COLUMN_EMPLOYEE_LAST_NAME} =  :lastName, " +
                "${EmployeeEntity.COLUMN_EMPLOYEE_AGE} =  :age, " +
                "${EmployeeEntity.COLUMN_EMPLOYEE_GENDER} =  :gender " +
                "WHERE ${EmployeeEntity.COLUMN_EMPLOYEE_ID} = :id"
    }
}