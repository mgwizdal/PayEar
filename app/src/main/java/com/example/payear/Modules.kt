package com.example.payear

import com.example.payear.db.PayEarDatabase
import com.example.payear.employee.model.EmployeesRepository
import com.example.payear.employee.viewmodel.EmployeesViewModel
import com.example.payear.utils.RxSchedulers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.android.viewmodel.ext.koin.viewModel


val appModule = module {
    single { RxSchedulers() }
    single { EmployeesRepository(get()) }
    viewModel { EmployeesViewModel(get()) }
}

val dbModule = module {
    single {
        PayEarDatabase.getInstance(androidApplication())
    }
    single {
        get<PayEarDatabase>().employeeDao()
    }
}