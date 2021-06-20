package model;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SalesTest extends BaseTest {

    Sales sales;
    LocalDate startWork;
    LocalDate findDate;
    List<BaseEmployee> employees = new ArrayList<>();

    @BeforeEach
    void setUp() {
        startWork = LocalDate.of(2005, 2, 14);
        sales = getSales(startWork, employees);
    }

    @Test
    void getSalary() {
        findDate = LocalDate.of(2015, 2, 14);
        double salaryValue = sales.getSalary(findDate);
        assertEquals(salaryValue, 110);
    }

    @Test
    void hasChief() {
        findDate = LocalDate.of(2015, 2, 14);
        employees.add(getEmployee(findDate));
        sales.setChildEmployee(employees);
        boolean hasChief = sales.hasChief();
        assertTrue(hasChief);
    }

    @Test
    void getBonusForEmployee() {
        findDate = LocalDate.of(2015, 2, 14);
        Employee employee = getEmployee(startWork);
        double employeeSalary = employee.getSalary(findDate);
        assertEquals(employeeSalary, 130);

        employees.add(employee);
        sales.setChildEmployee(employees);

        double salesBonus = sales.getBonus(findDate);
        assertEquals(salesBonus, 0.39);
    }

    @Test
    void getBonusForManager() {
        findDate = LocalDate.of(2015, 2, 14);
        Manager manager = getManager(startWork, emptyList());
        double employeeSalary = manager.getSalary(findDate);
        assertEquals(employeeSalary, 140);

        sales.setChildEmployee(singletonList(manager));

        double salesBonus = sales.getBonus(findDate);
        assertEquals(salesBonus, 0.42);
    }

    @Test
    void getBonusForSales() {
        findDate = LocalDate.of(2015, 2, 14);
        Sales childrenSales = getSales(startWork, emptyList());
        double employeeSalary = childrenSales.getSalary(findDate);
        assertEquals(employeeSalary, 110);

        sales.setChildEmployee(singletonList(childrenSales));

        double salesBonus = sales.getBonus(findDate);
        assertEquals(salesBonus, 0.33);
    }
}
