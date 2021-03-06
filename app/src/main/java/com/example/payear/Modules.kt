package com.example.payear

import com.example.payear.db.PayEarDatabase
import com.example.payear.employee.model.EmployeesRepository
import com.example.payear.employee.viewmodel.CreateItemDialogFragmentViewModel
import com.example.payear.employee.viewmodel.EmployeesViewModel
import com.example.payear.utils.RxSchedulers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { RxSchedulers() }
    single { EmployeesRepository(get(), get()) }
    viewModel { EmployeesViewModel(get(), get()) }
    viewModel { CreateItemDialogFragmentViewModel(get(), get()) }
}

val dbModule = module {
    single {
        PayEarDatabase.getInstance(androidApplication())
    }
    single {
        get<PayEarDatabase>().employeeDao()
    }
    single {
        get<PayEarDatabase>().addressDao()
    }
}