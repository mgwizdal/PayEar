package com.example.payear.employee.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Gender : Parcelable {
    MALE, FEMALE, OTHER
}