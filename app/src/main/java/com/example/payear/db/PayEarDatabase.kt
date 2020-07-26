package com.example.payear.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.payear.employee.model.db.AddressDao
import com.example.payear.employee.model.db.AddressEntity
import com.example.payear.employee.model.db.EmployeeDao
import com.example.payear.employee.model.db.EmployeeEntity


@Database(
    entities = [
        EmployeeEntity::class,
        AddressEntity::class
    ],
    version = 1
)
@TypeConverters(DatabaseConverters::class)
abstract class PayEarDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun addressDao(): AddressDao

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
