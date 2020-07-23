package com.example.payear.employee.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payear.R
import com.example.payear.employee.viewmodel.EmployeesViewModel
import com.example.payear.utils.include
import com.example.payear.utils.showToast
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class EmployeesActivity : AppCompatActivity() {
    private val destroyDisposables = CompositeDisposable()

    private val viewModel: EmployeesViewModel by viewModel()
    private lateinit var adapter: EmployeesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupData()
        setupFab()
    }

    private fun setupRecyclerView() {
        adapter = EmployeesAdapter()
        employeesRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        employeesRecyclerView.adapter = adapter
        adapter.onClickListener = {
            showToast("Employee name: $it")
        }
    }

    private fun setupData() {
        destroyDisposables include viewModel.employees
            .subscribe {
                adapter.replaceData(it)
            }
    }

    private fun setupFab() {
        createFab.setOnClickListener {
            destroyDisposables include viewModel.insertNewItem()
                .subscribe { showToast(getString(R.string.new_employee_added)) }
        }
    }

    override fun onDestroy() {
        destroyDisposables.dispose()
        super.onDestroy()
    }
}