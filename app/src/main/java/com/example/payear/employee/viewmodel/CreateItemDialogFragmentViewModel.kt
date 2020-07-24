package com.example.payear.employee.viewmodel

import androidx.lifecycle.ViewModel
import com.example.payear.employee.model.EmployeeItem
import com.example.payear.employee.model.EmployeesRepository
import com.example.payear.utils.RxSchedulers
import io.reactivex.Completable
import java.lang.IllegalArgumentException

class CreateItemDialogFragmentViewModel(
    private val employeesRepository: EmployeesRepository,
    private val rxSchedulers: RxSchedulers
) : ViewModel() {

    fun insertNewItem(employeeItem: EmployeeItem): Completable {
        return Completable.fromAction {
            employeesRepository.insertEmployee(
                EmployeeItem(
                    null,
                    firstName = employeeItem.firstName.throwWhenEmpty(),
                    lastName = employeeItem.lastName.throwWhenEmpty(),
                    age = employeeItem.age,
                    gender = employeeItem.gender,
                    address = employeeItem.address
                )
            )
        }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.ui)
    }

    private fun String.throwWhenEmpty(): String {
        return if (this.isEmpty()) throw IllegalArgumentException() else this
    }
}