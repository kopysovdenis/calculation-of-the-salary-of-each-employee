package model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@Slf4j
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
        log.info("Start test: hasChief");
        findDate = LocalDate.of(2015, 2, 14);
        boolean hasChief = employee.hasLeader();
        assertFalse(hasChief);
        log.info("Result: hasChief equals false");
        log.info("End test: hasChief");
    }

    /**
     * <p>Payroll calculation up to the limit</p>
     * <p>term of work: 8 years</p>
     * <p>base rate: 100</p>
     * <p>interest per year: 3%</p>
     * <p>bonus limit: 30% of the base rate</p>
     */
    @Test
    void getSalaryUpToLimit() {
        log.info("Start test: Payroll calculation up to the limit");
        findDate = LocalDate.of(2013, 2, 14);
        var salaryValue = employee.getSalary(findDate);
        assertEquals(salaryValue, 124);
        log.info(String.format("Result: %s equals 130", salaryValue));
        log.info("End test: getSalary");
    }

    /**
     * <p>Payroll calculation above the limit</p>
     * <p>term of work: 20 years</p>
     * <p>base rate: 100</p>
     * <p>interest per year: 3%</p>
     * <p>bonus limit: 30% of the base rate</p>
     */
    @Test
    void getSalaryAboveToLimit() {
        log.info("Start test: Payroll calculation above the limit");
        findDate = LocalDate.of(2025, 2, 14);
        var salaryValue = employee.getSalary(findDate);
        assertEquals(salaryValue, 130);
        log.info(String.format("Result: %s equals 130", salaryValue));
        log.info("End test: getSalary");
    }
}
