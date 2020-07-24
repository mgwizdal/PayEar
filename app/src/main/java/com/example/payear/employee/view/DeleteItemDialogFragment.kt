package com.example.payear.employee.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.payear.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_delete_item.view.*

class DeleteItemDialogFragment : BottomSheetDialogFragment() {

    private var deleteId: Int? = null
    private lateinit var name: String
    private var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as? Listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deleteId = requireNotNull(arguments?.getInt(KEY_DELETE_ID))
        name = requireNotNull(arguments?.getString(KEY_DELETE_NAME))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_delete_item, container, false)
        view.deleteLabelTextView.text = getString(R.string.are_you_sure_you_want_to_remove, name)
        view.confirmDeleteButton.setOnClickListener {
            deleteId?.let { listener?.onDeleteClicked(it) }
            dialog?.dismiss()
        }
        view.dismissDeleteButton.setOnClickListener {
            dialog?.dismiss()
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        const val TAG = "DeleteItemDialogFragment"
        const val KEY_DELETE_ID = "KEY_DELETE_ID"
        const val KEY_DELETE_NAME = "KEY_DELETE_NAME"
        fun newInstance(id: Int, name: String): DeleteItemDialogFragment {
            val bundle = Bundle()
            bundle.putInt(KEY_DELETE_ID, id)
            bundle.putString(KEY_DELETE_NAME, name)
            val fragment =
                DeleteItemDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    interface Listener {
        fun onDeleteClicked(id: Int)
    }
}
