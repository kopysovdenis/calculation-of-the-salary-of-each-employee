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

class ManagerTest extends BaseTest {

    Manager manager;
    LocalDate startWork;
    LocalDate findDate;
    List<BaseEmployee> employees = new ArrayList<>();

    @BeforeEach
    void setUp() {
        startWork = LocalDate.of(2005, 2, 14);
        manager = getManager(startWork, employees);
    }

    @Test
    void hasChief() {
        findDate = LocalDate.of(2015, 2, 14);
        manager.setChildEmployee(singletonList(getEmployee(findDate)));
        boolean hasChief = manager.hasChief();
        assertTrue(hasChief);
    }

    @Test
    void getSalary() {
        findDate = LocalDate.of(2015, 2, 14);
        double salaryValue = manager.getSalary(findDate);
        assertEquals(salaryValue, 140);
    }

    @Test
    void getBonusForEmployee() {
        findDate = LocalDate.of(2015, 2, 14);
        Employee employee = getEmployee(startWork);
        double employeeSalary = employee.getSalary(findDate);
        assertEquals(employeeSalary, 130);

        employees.add(employee);
        manager.setChildEmployee(employees);

        double salesBonus = manager.getBonus(findDate);
        assertEquals(salesBonus, 0.65);
    }

    @Test
    void getBonusForManager() {
        findDate = LocalDate.of(2015, 2, 14);
        Manager childrenManager = getManager(startWork, emptyList());
        double employeeSalary = childrenManager.getSalary(findDate);
        assertEquals(employeeSalary, 140);

        manager.setChildEmployee(singletonList(childrenManager));

        double salesBonus = manager.getBonus(findDate);
        assertEquals(salesBonus, 0.7000000000000001);
    }

    @Test
    void getBonusForSales() {
        findDate = LocalDate.of(2015, 2, 14);
        Sales childrenSales = getSales(startWork, emptyList());
        double employeeSalary = childrenSales.getSalary(findDate);
        assertEquals(employeeSalary, 110);

        manager.setChildEmployee(singletonList(childrenSales));

        double salesBonus = manager.getBonus(findDate);
        assertEquals(salesBonus, 0.55);
    }
}
