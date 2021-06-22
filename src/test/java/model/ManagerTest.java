package model;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
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
        boolean hasChief = manager.hasLeader();
        assertTrue(hasChief);
    }

    /**
     * <p>Payroll calculation up to the limit</p>
     * <p>term of work: 10 years</p>
     * <p>base rate: 100</p>
     * <p>interest per year: 5%</p>
     * <p>bonus limit: 40% of the base rate</p>
     */
    @Test
    void getSalaryUpToLimit() {
        log.info("Start test: Payroll calculation up to the limit");
        findDate = LocalDate.of(2015, 2, 14);
        double salaryValue = manager.getSalary(findDate);
        assertEquals(salaryValue, 140);
        log.info(String.format("Result: %s equals 140", salaryValue));
        log.info("End test: Payroll calculation up to the limit");
    }


    /**
     * <p>Payroll calculation above the limit</p>
     * <p>term of work: 5 years</p>
     * <p>base rate: 100</p>
     * <p>interest per year: 5%</p>
     * <p>bonus limit: 40% of the base rate</p>
     */
    @Test
    void getSalaryAboveToLimit() {
        log.info("Start test: Payroll calculation above the limit");
        findDate = LocalDate.of(2010, 2, 14);
        double salaryValue = manager.getSalary(findDate);
        assertEquals(salaryValue, 125);
        log.info(String.format("Result: %s equals 125", salaryValue));
        log.info("End test: Payroll calculation above the limit");
    }

    /**
     * <p>Bonus calculation with subordinate: employee</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>percentage of the salary of all subordinates: 0,5%</p>
     */
    @Test
    void getBonusForEmployee() {
        log.info("Start test: Bonus calculation with subordinate: employee");
        findDate = LocalDate.of(2015, 2, 14);
        Employee employee = getEmployee(startWork);
        double employeeSalary = employee.getSalary(findDate);
        assertEquals(employeeSalary, 130);

        employees.add(employee);
        manager.setChildEmployee(employees);

        double managerBonus = manager.getBonus(findDate);
        assertEquals(managerBonus, 0.65);
        log.info(String.format("Result: %s equals 0.65", managerBonus));
        log.info("End test: Bonus calculation with subordinate: employee");
    }

    /**
     * <p>Bonus calculation with subordinate: manager</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>percentage of the salary of all subordinates: 0,5%</p>
     */
    @Test
    void getBonusForManager() {
        log.info("Start test: Bonus calculation with subordinate: manager");
        findDate = LocalDate.of(2015, 2, 14);
        Manager childrenManager = getManager(startWork, emptyList());
        double managerSalary = childrenManager.getSalary(findDate);
        assertEquals(managerSalary, 140);

        manager.setChildEmployee(singletonList(childrenManager));

        double managerBonus = manager.getBonus(findDate);
        assertEquals(managerBonus, 0.7000000000000001);
        log.info(String.format("Result: %s equals 0.7000000000000001", managerBonus));
        log.info("End test: Bonus calculation with subordinate: manager");
    }

    /**
     * <p>Bonus calculation with subordinate: sales</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>percentage of the salary of all subordinates: 0,5%</p>
     */
    @Test
    void getBonusForSales() {
        log.info("Start test: Bonus calculation with subordinate: manager");
        findDate = LocalDate.of(2015, 2, 14);
        Sales childrenSales = getSales(startWork, emptyList());
        double salesSalary = childrenSales.getSalary(findDate);
        assertEquals(salesSalary, 110);

        manager.setChildEmployee(singletonList(childrenSales));

        double managerBonus = manager.getBonus(findDate);
        assertEquals(managerBonus, 0.55);
        log.info(String.format("Result: %s equals 0.55", managerBonus));
        log.info("End test: Bonus calculation with subordinate: manager");
    }

    /**
     * <p>Salary calculation with 10 subordinate: employee</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>percentage of the salary of all subordinates: 0,5%</p>
     */
    @Test
    void getSalaryToMoreChildrenForOneRow() {
        log.info("Start test: Salary calculation with 10 subordinate: employee");
        findDate = LocalDate.of(2015, 2, 14);
        List<BaseEmployee> listChildren = new ArrayList<>();
        for (int count = 0; count < 10; count++) {
            listChildren.add(getEmployee(startWork));
        }

        Manager manager = getManager(startWork, listChildren);
        double managerSalary = manager.getSalary(findDate);
        assertEquals(managerSalary, 146.5);
        log.info(String.format("Result: %s equals 146.5", managerSalary));
        log.info("End test: Salary calculation with 10 subordinate: employee");
    }

    /**
     * <p>Bonus calculation with 25 subordinates</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>percentage of the salary of all subordinates: 0,5%</p>
     */
    @Test
    void getBonusToMoreChildrenForManager() {
        log.info("Start test: Bonus calculation with 25 subordinates");
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
        assertEquals(bonus, 0.5642169265077952);
        log.info(String.format("Result: %s equals 0.5642169265077952", bonus));
        log.info("End test: Bonus calculation with 25 subordinates");
    }

    /**
     * <p>Salary calculation with 25 subordinates</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>percentage of the salary of all subordinates: 0,5%</p>
     */
    @Test
    void getSalaryToMoreChildrenForManager() {
        log.info("Start test: Salary calculation with 25 subordinates");
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
        log.info(String.format("Result: %s equals 140.56421692650778", salary));
        log.info("End test: Salary calculation with 25 subordinates");
    }
}
