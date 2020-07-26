package com.example.payear.employee.model

import com.example.payear.employee.model.db.*
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class EmployeesRepositoryTest {

    @Mock
    private lateinit var employeeDao: EmployeeDao
    @Mock
    private lateinit var addressDao: AddressDao

    lateinit var repository: EmployeesRepository

    private val employeeEntity =
        EmployeeEntity(
            0,
            "firstName",
            "lastName",
            21,
            Gender.OTHER
        )
    private val addressEntity = AddressEntity(0, employeeEntity.id!!, "address")
    private val listOfAddresses = listOf<AddressItem>(addressEntity.toItem())
    private val employeeItem = EmployeeItem(
        employeeEntity.id,
        employeeEntity.firstName,
        employeeEntity.lastName,
        employeeEntity.age,
        employeeEntity.gender,
        listOfAddresses
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = EmployeesRepository(employeeDao, addressDao)
    }

    @Test
    fun `Get all employees`() {
        //given
        `when`(employeeDao.getEmployeesView()).thenReturn(Flowable.just(listOf(
            EmployeeWithAddressView().apply {
                this.employeeEntity = this@EmployeesRepositoryTest.employeeEntity
                this.addressList = listOfAddresses.map { it.toEntity() }
            }  )))

        //when
        val observer = repository.getAllEmployees().test()

        //then
        observer.assertValue(
            listOf(
                EmployeeItem(
                    employeeEntity.id,
                    employeeEntity.firstName,
                    employeeEntity.lastName,
                    employeeEntity.age,
                    employeeEntity.gender,
                    listOfAddresses
                )
            )
        )
    }

    @Test
    fun `Delete employee`() {
        //when
        repository.deleteById(12)

        //then
        verify(employeeDao).deleteById(12)
    }

    @Test
    fun `Update employee with correct id`() {
        //when
        repository.updateEmployee(employeeItem)

        //then
        verify(employeeDao).updateEmployee(
            employeeItem.id ?: throw IllegalStateException(),
            employeeItem.firstName,
            employeeItem.lastName,
            employeeItem.age,
            employeeItem.gender.name
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Update employee with wrong id`() {
        //when
        repository.updateEmployee(employeeItem.copy(id = null))
    }
}