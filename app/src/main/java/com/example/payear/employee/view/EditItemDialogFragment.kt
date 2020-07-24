package com.example.payear.employee.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.payear.R
import com.example.payear.employee.model.EmployeeItem
import com.example.payear.employee.model.Gender
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_edit_item.view.*

class EditItemDialogFragment: BottomSheetDialogFragment() {

    private var id: Int? = null
    private lateinit var firstName: String
    private lateinit var lastName: String
    private var age: Int? = null
    private lateinit var gender: Gender

    private var listener: Listener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as? Listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = requireNotNull(arguments?.getInt(KEY_EDIT_ID))
        firstName = requireNotNull(arguments?.getString(KEY_EDIT_FIRST_NAME))
        lastName = requireNotNull(arguments?.getString(KEY_EDIT_LAST_NAME))
        age = requireNotNull(arguments?.getInt(KEY_EDIT_AGE))
        gender = Gender.valueOf(requireNotNull(arguments?.getString(KEY_EDIT_GENDER)))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_edit_item, container, false)
        view.ageEditText.setText(age.toString())
        view.firstNameEditText.setText(firstName)
        view.lastNameEditText.setText(lastName)

        view.confirmEditButton.setOnClickListener {
            id?.let {
                listener?.onEditClicked(
                    EmployeeItem(
                        it,
                        view.firstNameEditText.text.toString(),
                        view.lastNameEditText.text.toString(),
                        Integer.parseInt(view.ageEditText.text.toString()),
                        Gender.MALE
                    )) }
            dialog?.dismiss()
        }
        view.cancelEditButton.setOnClickListener {
            dialog?.dismiss()
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        const val TAG = "EditItemDialogFragment"
        const val KEY_EDIT_ID = "KEY_EDIT_ID"
        const val KEY_EDIT_FIRST_NAME = "KEY_EDIT_FIRST_NAME"
        const val KEY_EDIT_LAST_NAME = "KEY_EDIT_LAST_NAME"
        const val KEY_EDIT_AGE = "KEY_EDIT_AGE"
        const val KEY_EDIT_GENDER = "KEY_EDIT_GENDER"

        fun newInstance(id: Int, firstName: String, lastName: String, age: Int, gender: Gender): EditItemDialogFragment {
            val bundle = Bundle()
            bundle.putInt(KEY_EDIT_ID, id)
            bundle.putInt(KEY_EDIT_AGE, age)
            bundle.putString(KEY_EDIT_LAST_NAME, lastName)
            bundle.putString(KEY_EDIT_FIRST_NAME, firstName)
            bundle.putString(KEY_EDIT_GENDER, gender.toString())
            val fragment =
                EditItemDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    interface Listener {
        fun onEditClicked(employeeItem: EmployeeItem)
    }
}