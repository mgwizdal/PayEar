package com.example.payear.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.payear.employee.model.EmployeeDao
import com.example.payear.employee.model.EmployeeEntity


@Database(
    entities = [
        EmployeeEntity::class
    ],
    version = 1
)
@TypeConverters(DatabaseConverters::class)
abstract class PayEarDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        private const val DB_NAME = "PayEarDatabase"

        fun getInstance(context: Context): PayEarDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                PayEarDatabase::class.java,
                DB_NAME
            )
                .build()
        }
    }
}
