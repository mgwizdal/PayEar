package com.example.payear.employee.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payear.R
import com.example.payear.employee.model.EmployeeItem
import com.example.payear.employee.viewmodel.EmployeesViewModel
import com.example.payear.utils.include
import com.example.payear.utils.showDialogIfNotAdded
import com.example.payear.utils.showToast
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class EmployeesActivity : AppCompatActivity(), DeleteItemDialogFragment.Listener,
    EditItemDialogFragment.Listener {
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
        adapter.onEditClickListener = { item ->
            if (item.id != null) {
                showDialogIfNotAdded(
                    EditItemDialogFragment.TAG,
                    EditItemDialogFragment.newInstance(
                        item.id,
                        item.firstName,
                        item.lastName,
                        item.age,
                        item.gender,
                        item.address
                    )
                )
            } else {
                showToast(getString(R.string.issue_occurred))
            }
        }

        adapter.onDeleteClickListener = { id, name ->
            showDialogIfNotAdded(
                DeleteItemDialogFragment.TAG,
                DeleteItemDialogFragment.newInstance(id, name)
            )
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
            showDialogIfNotAdded(
                CreateItemDialogFragment.TAG,
                CreateItemDialogFragment.newInstance()
            )
        }
    }

    override fun onDeleteClicked(id: Int) {
        destroyDisposables include viewModel.deleteItem(id)
            .subscribe {
                showToast(getString(R.string.employee_removed))
            }
    }

    override fun onEditClicked(employeeItem: EmployeeItem) {
        destroyDisposables include viewModel.updateItem(employeeItem)
            .subscribe {
                showToast(getString(R.string.employee_updated))
            }
    }

    override fun onDestroy() {
        destroyDisposables.dispose()
        super.onDestroy()
    }
}