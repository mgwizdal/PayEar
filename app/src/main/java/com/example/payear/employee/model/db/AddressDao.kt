package com.example.payear.employee.model.db

import androidx.room.*

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddress(vararg addressEntities: AddressEntity): Array<Long>

    @Transaction
    fun updateAddressList(
        id: Int,
        addressArray: Array<AddressEntity>
    ) {
        deleteAddressByEmployeeId(id)
        insertAddress(*addressArray)
    }

    @Query("DELETE FROM address WHERE employee_id = :employeeId")
    fun deleteAddressByEmployeeId(employeeId: Int)
}
