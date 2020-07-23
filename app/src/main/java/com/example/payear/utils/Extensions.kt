package com.example.payear.utils

import android.content.Context
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


infix fun CompositeDisposable.include(observable: Disposable) {
    this.add(observable)
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}