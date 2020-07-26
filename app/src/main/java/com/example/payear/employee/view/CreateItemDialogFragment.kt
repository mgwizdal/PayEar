package com.example.payear.employee.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.payear.R
import com.example.payear.employee.model.AddressItem
import com.example.payear.employee.model.EmployeeItem
import com.example.payear.employee.viewmodel.CreateItemDialogFragmentViewModel
import com.example.payear.utils.include
import com.example.payear.utils.showToast
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dialog_edit_item.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class CreateItemDialogFragment : BaseItemDialogFragment() {

    private val destroyViewDisposable = CompositeDisposable()

    private val viewModel: CreateItemDialogFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
            ?: throw ErrorWhenInflatingView()

        view.confirmButton.text = getString(R.string.create)
        view.confirmButton.setOnClickListener {
            destroyViewDisposable include viewModel.insertNewItem(
                EmployeeItem(
                    null,
                    view.firstNameEditText.text.toString(),
                    view.lastNameEditText.text.toString(),
                    Integer.parseInt(view.ageEditText.text.let { if (it.isNotEmpty()) it.toString() else "0" }),
                    getGenderFromId(view.genderRadioGroup.checkedRadioButtonId),
                    listOf(
                        AddressItem(null, null, view.addressEditText.text.toString()),
                        AddressItem(null, null, view.addressEditText2.text.toString())
                    )
                    )
            ).subscribe({
                dialog?.dismiss()
                view.context.showToast(getString(R.string.new_employee_added))
            }, {
                dialog?.dismiss()
                view.context.showToast(getString(R.string.issue_occurred))
            })
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyViewDisposable.dispose()
    }

    companion object {
        const val TAG = "CreateItemDialogFragment"
        fun newInstance(): CreateItemDialogFragment {
            return CreateItemDialogFragment()
        }
    }
}