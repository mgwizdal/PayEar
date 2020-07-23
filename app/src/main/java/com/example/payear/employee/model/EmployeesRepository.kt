package com.example.payear.employee.model

import io.reactivex.Flowable
import io.reactivex.Observable

class EmployeesRepository(private val employeeDao: EmployeeDao) {

    fun insertEmployee(employeeItem: EmployeeItem) = employeeDao.insertEmployees(
        EmployeeEntity(
            firstName = employeeItem.firstName,
            lastName = employeeItem.lastName,
            age = employeeItem.age,
            gender = employeeItem.gender ))

    fun getAllEmployees(): Observable<List<EmployeeItem>> =
        employeeDao.getEmployees().toObservable().map { list -> list.map { it.toEmployeeItem() } }

    fun getEmployeeById(id: Int): Flowable<EmployeeItem> =
        employeeDao.getEmployeeById(id).map { it.toEmployeeItem() }

    fun deleteById(id: Int) = employeeDao.deleteById(id)

    fun updateById(id: Int, newEntity: EmployeeEntity) = employeeDao.updateById(
        id,
        newEntity.firstName,
        newEntity.lastName,
        newEntity.age,
        newEntity.gender.name
    )

    private fun EmployeeEntity.toEmployeeItem(): EmployeeItem =
        EmployeeItem(id, firstName, lastName, age, gender)
}