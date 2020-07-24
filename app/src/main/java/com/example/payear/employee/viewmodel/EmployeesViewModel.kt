package com.example.payear.employee.viewmodel

import androidx.lifecycle.ViewModel
import com.example.payear.employee.model.EmployeeItem
import com.example.payear.employee.model.EmployeesRepository
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

    fun deleteItem(id: Int): Completable {
        return Completable.fromAction { employeesRepository.deleteById(id) }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.ui)
    }

    fun updateItem(item: EmployeeItem): Completable {
        return Completable.fromAction { employeesRepository.updateEmployee(item) }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.ui)
    }
}