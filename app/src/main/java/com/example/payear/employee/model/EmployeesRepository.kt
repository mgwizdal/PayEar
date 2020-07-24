package com.example.payear.employee.model

import io.reactivex.Flowable
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class EmployeesRepository(private val employeeDao: EmployeeDao) {

    fun insertEmployee(employeeItem: EmployeeItem) = employeeDao.insertEmployees(
        EmployeeEntity(
            firstName = employeeItem.firstName,
            lastName = employeeItem.lastName,
            age = employeeItem.age,
            gender = employeeItem.gender
        )
    )

    fun getAllEmployees(): Observable<List<EmployeeItem>> =
        employeeDao.getEmployees().toObservable().map { list -> list.map { it.toEmployeeItem() } }

    fun getEmployeeById(id: Int): Flowable<EmployeeItem> =
        employeeDao.getEmployeeById(id).map { it.toEmployeeItem() }

    fun deleteById(id: Int) = employeeDao.deleteById(id)

    fun updateById(item: EmployeeItem) = employeeDao.updateById(
        item.id ?: throw IllegalArgumentException(),
        item.firstName,
        item.lastName,
        item.age,
        item.gender.name
    )

    private fun EmployeeEntity.toEmployeeItem(): EmployeeItem =
        EmployeeItem(id, firstName, lastName, age, gender)
}