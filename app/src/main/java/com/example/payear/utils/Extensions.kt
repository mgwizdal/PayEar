package com.example.payear.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


infix fun CompositeDisposable.include(observable: Disposable) {
    this.add(observable)
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun FragmentActivity.showDialogIfNotAdded(tag: String, dialog: DialogFragment) {
    supportFragmentManager?.findFragmentByTag(tag)?.isAdded?.let { if (it) return }
    dialog.showNow(supportFragmentManager, tag)
}