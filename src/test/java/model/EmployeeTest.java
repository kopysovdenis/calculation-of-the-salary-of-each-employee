package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmployeeTest {

    Employee employee;
    LocalDate startWork;
    LocalDate findDate;

    @BeforeEach
    void setUp() {
        startWork = LocalDate.of(2005, 2, 14);
        employee = new Employee(startWork);
    }

    @Test
    void hasChief() {
        findDate = LocalDate.of(2015, 2, 14);
        boolean hasChief = employee.hasChief();
        assertFalse(hasChief);
    }

    @Test
    void getSalary() {
        findDate = LocalDate.of(2015, 2, 14);
        var salaryValue = employee.getSalary(findDate);
        assertEquals(salaryValue, 130);
    }
}
