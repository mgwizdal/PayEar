package com.example.payear.employee.model

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

    lateinit var repository: EmployeesRepository

    private val entity = EmployeeEntity(
        0,
        "firstName",
        "lastName",
        21,
        Gender.OTHER,
        "Zwycieska"
    )

    private val employeeItem = EmployeeItem(
        entity.id,
        entity.firstName,
        entity.lastName,
        entity.age,
        entity.gender,
        entity.address
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = EmployeesRepository(employeeDao)
    }

    @Test
    fun `Get all employees`() {
        //given
        `when`(employeeDao.getEmployees()).thenReturn(Flowable.just(listOf(entity)))

        //when
        val observer = repository.getAllEmployees().test()

        //then
        observer.assertValue(
            listOf(
                EmployeeItem(
                    entity.id,
                    entity.firstName,
                    entity.lastName,
                    entity.age,
                    entity.gender,
                    entity.address
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
            employeeItem.gender.name,
            employeeItem.address
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Update employee with wrong id`() {
        //when
        repository.updateEmployee(employeeItem.copy(id = null))
    }
}