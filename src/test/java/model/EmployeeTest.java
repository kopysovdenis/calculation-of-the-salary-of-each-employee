package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class EmployeeTest {

    Employee employee;
    LocalDate startWork;
    LocalDate findDate;

    @BeforeEach
    void setUp() {
        startWork = LocalDate.of(2015, 2, 14);
        findDate = LocalDate.of(2015, 2, 14);
        employee = new Employee(startWork);
    }

    @Test
    void getSalary() {
        var salary = employee.getSalary(findDate);
        System.out.println(salary);
    }
}
