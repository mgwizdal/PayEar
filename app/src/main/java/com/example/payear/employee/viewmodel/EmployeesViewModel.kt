package com.example.payear.employee.viewmodel

import androidx.lifecycle.ViewModel
import com.example.payear.employee.model.EmployeesRepository

class EmployeesViewModel(employeesRepository: EmployeesRepository): ViewModel() {

    val employees = employeesRepository.getAllEmployees()


}