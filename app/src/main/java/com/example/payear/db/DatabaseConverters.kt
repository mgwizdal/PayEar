package com.example.payear.db

import androidx.room.TypeConverter
import com.example.payear.employee.model.Gender

object DatabaseConverters {

    @TypeConverter
    @JvmStatic
    fun decodeGender(type: String) = Gender.valueOf(type)

    @TypeConverter
    @JvmStatic
    fun encodeGender(gender: Gender) = gender.name
}