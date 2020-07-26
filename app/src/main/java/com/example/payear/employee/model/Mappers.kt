package com.example.payear.employee.model

import com.example.payear.employee.model.db.AddressEntity

fun AddressItem.toEntity() = AddressEntity(id, employeeId ?: throw IllegalArgumentException("Address item employeeId is null"), address)

fun AddressEntity.toItem() = AddressItem(id, employeeId, address)