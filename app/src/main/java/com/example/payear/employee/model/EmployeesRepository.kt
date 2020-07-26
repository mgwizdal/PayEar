package com.example.payear.employee.model

import com.example.payear.employee.model.db.*
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class EmployeesRepository(
    private val employeeDao: EmployeeDao,
    private val addressDao: AddressDao
) {

    fun insertEmployee(employeeItem: EmployeeItem) {
        val result = employeeDao.insertEmployees(
            EmployeeEntity(
                firstName = employeeItem.firstName,
                lastName = employeeItem.lastName,
                age = employeeItem.age,
                gender = employeeItem.gender
            )
        )

        addressDao.insertAddress(*employeeItem.addressList.map {
            AddressEntity(
                it.id,
                result[0].toInt(),
                it.address
            )
        }.toTypedArray())
    }

    fun getAllEmployees(): Observable<List<EmployeeItem>> =
        employeeDao.getEmployeesView().toObservable()
            .map { list -> list.map { it.toEmployeeItem() } }

    fun deleteById(id: Int) = employeeDao.deleteById(id)

    fun updateEmployee(item: EmployeeItem) {
        employeeDao.updateEmployee(
            item.id ?: throw IllegalArgumentException(),
            item.firstName,
            item.lastName,
            item.age,
            item.gender.name
        )

        addressDao.updateAddressList(item.id, item.addressList.map { it.toEntity() }.toTypedArray())
    }

    private fun EmployeeWithAddressView.toEmployeeItem(): EmployeeItem =
        EmployeeItem(
            employeeEntity.id,
            employeeEntity.firstName,
            employeeEntity.lastName,
            employeeEntity.age,
            employeeEntity.gender,
            addressList.map { AddressItem(it.id, it.employeeId, it.address) }
        )
}