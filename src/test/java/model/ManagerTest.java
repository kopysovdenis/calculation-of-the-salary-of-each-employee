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

    @Test
    void getSalaryToMoreChildrenForOneRow() {
        findDate = LocalDate.of(2015, 2, 14);
        List<BaseEmployee> listChildren = new ArrayList<>();
        for(int count = 0; count < 10; count++) {
            listChildren.add(getEmployee(startWork));
        }

        Manager manager = getManager(startWork, listChildren);
        double salary = manager.getSalary(findDate);
        assertEquals(salary, 146.5);
    }

    @Test
    void getBonusToMoreChildrenForManager() {
        findDate = LocalDate.of(2015, 2, 14);
        Employee employee25 = getEmployee(startWork);
        Manager manager24 = getManager(startWork, singletonList(employee25));
        Sales sales23 = getSales(startWork, singletonList(manager24));
        Manager manager22 = getManager(startWork, singletonList(sales23));
        Sales sales21 = getSales(startWork, singletonList(manager22));
        Manager manager20 = getManager(startWork, singletonList(sales21));
        Sales sales19 = getSales(startWork, singletonList(manager20));
        Manager manager18 = getManager(startWork, singletonList(sales19));
        Sales sales17 = getSales(startWork, singletonList(manager18));
        Manager manager16 = getManager(startWork, singletonList(sales17));
        Sales sales15 = getSales(startWork, singletonList(manager16));
        Manager manager14 = getManager(startWork, singletonList(sales15));
        Sales sales13 = getSales(startWork, singletonList(manager14));
        Manager manager12 = getManager(startWork, singletonList(sales13));
        Sales sales11 = getSales(startWork, singletonList(manager12));
        Manager manager10 = getManager(startWork, singletonList(sales11));
        Sales sales9 = getSales(startWork, singletonList(manager10));
        Manager manager8 = getManager(startWork, singletonList(sales9));
        Sales sales7 = getSales(startWork, singletonList(manager8));
        Manager manager6 = getManager(startWork, singletonList(sales7));
        Sales sales5 = getSales(startWork, singletonList(manager6));
        Manager manager4 = getManager(startWork, singletonList(sales5));
        Sales sales3 = getSales(startWork, singletonList(manager4));
        Manager manager2 = getManager(startWork, singletonList(sales3));
        Sales sales1 = getSales(startWork, singletonList(manager2));
        Manager managerChief = getManager(startWork, singletonList(sales1));
        double bonus = managerChief.getBonus(findDate);
        assertEquals(bonus,0.5642169265077952);
    }

    @Test
    void getSalaryToMoreChildrenForManager() {
        findDate = LocalDate.of(2015, 2, 14);
        Employee employee25 = getEmployee(startWork);
        Manager manager24 = getManager(startWork, singletonList(employee25));
        Sales sales23 = getSales(startWork, singletonList(manager24));
        Manager manager22 = getManager(startWork, singletonList(sales23));
        Sales sales21 = getSales(startWork, singletonList(manager22));
        Manager manager20 = getManager(startWork, singletonList(sales21));
        Sales sales19 = getSales(startWork, singletonList(manager20));
        Manager manager18 = getManager(startWork, singletonList(sales19));
        Sales sales17 = getSales(startWork, singletonList(manager18));
        Manager manager16 = getManager(startWork, singletonList(sales17));
        Sales sales15 = getSales(startWork, singletonList(manager16));
        Manager manager14 = getManager(startWork, singletonList(sales15));
        Sales sales13 = getSales(startWork, singletonList(manager14));
        Manager manager12 = getManager(startWork, singletonList(sales13));
        Sales sales11 = getSales(startWork, singletonList(manager12));
        Manager manager10 = getManager(startWork, singletonList(sales11));
        Sales sales9 = getSales(startWork, singletonList(manager10));
        Manager manager8 = getManager(startWork, singletonList(sales9));
        Sales sales7 = getSales(startWork, singletonList(manager8));
        Manager manager6 = getManager(startWork, singletonList(sales7));
        Sales sales5 = getSales(startWork, singletonList(manager6));
        Manager manager4 = getManager(startWork, singletonList(sales5));
        Sales sales3 = getSales(startWork, singletonList(manager4));
        Manager manager2 = getManager(startWork, singletonList(sales3));
        Sales sales1 = getSales(startWork, singletonList(manager2));
        Manager managerChief = getManager(startWork, singletonList(sales1));
        double salary = managerChief.getSalary(findDate);
        assertEquals(salary, 140.56421692650778);
    }
}
