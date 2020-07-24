package com.example.payear.employee.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.payear.R
import com.example.payear.employee.model.Gender
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_edit_item.view.*

abstract class BaseItemDialogFragment : BottomSheetDialogFragment() {

    var listener: EditItemDialogFragment.Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_edit_item, container, false)
        view.cancelButton.setOnClickListener {
            dialog?.dismiss()
        }
        return view
    }

    fun getGenderFromId(id: Int): Gender {
        return when (id) {
            R.id.maleRadioButton -> Gender.MALE
            R.id.femaleRadioButton -> Gender.FEMALE
            else -> Gender.OTHER
        }
    }
}
class ErrorWhenInflatingView: Throwable()