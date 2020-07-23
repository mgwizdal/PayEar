package com.example.payear.employee.viewmodel

import androidx.lifecycle.ViewModel
import com.example.payear.employee.model.EmployeeItem
import com.example.payear.employee.model.EmployeesRepository
import com.example.payear.employee.model.Gender
import com.example.payear.utils.RxSchedulers
import io.reactivex.Completable
import io.reactivex.Observable

class EmployeesViewModel(
    private val employeesRepository: EmployeesRepository,
    private val rxSchedulers: RxSchedulers
) : ViewModel() {

    val employees: Observable<List<EmployeeItem>> = employeesRepository.getAllEmployees()
        .subscribeOn(rxSchedulers.io)
        .observeOn(rxSchedulers.ui)

    fun insertNewItem(): Completable {
        return Completable.fromAction {
            employeesRepository.insertEmployee(
                EmployeeItem(
                    null,
                    firstName = "Max",
                    lastName = "Gwizdala",
                    age = 26,
                    gender = Gender.MALE
                )
            )
        }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.ui)
    }
}