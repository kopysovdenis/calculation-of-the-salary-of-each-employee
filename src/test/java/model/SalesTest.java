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
    void hasChief() {
        findDate = LocalDate.of(2015, 2, 14);
        employees.add(getEmployee(findDate));
        sales.setChildEmployee(employees);
        boolean hasChief = sales.hasLeader();
        assertTrue(hasChief);
    }


    /**
     * <p>Payroll calculation up to the limit</p>
     * <p>term of work: 50 years</p>
     * <p>base rate: 100</p>
     * <p>interest per year: 1%</p>
     * <p>bonus limit: 35% of the base rate</p>
     */
    @Test
    void getSalaryUpToLimit() {
        log.info("Start test: Payroll calculation up to the limit");
        findDate = LocalDate.of(2055, 2, 14);
        double salaryValue = sales.getSalary(findDate);
        assertEquals(salaryValue, 135);
        log.info(String.format("Result: %s equals 135", salaryValue));
        log.info("End test: Payroll calculation up to the limit");
    }

    /**
     * <p>Payroll calculation above the limit</p>
     * <p>term of work: 10 years</p>
     * <p>base rate: 100</p>
     * <p>interest per year: 1%</p>
     * <p>bonus limit: 35% of the base rate</p>
     */
    @Test
    void getSalaryAboveToLimit() {
        log.info("Start test: Payroll calculation above the limit");
        findDate = LocalDate.of(2015, 2, 14);
        double salaryValue = sales.getSalary(findDate);
        assertEquals(salaryValue, 110);
        log.info(String.format("Result: %s equals 135", salaryValue));
        log.info("End test: Payroll calculation above the limit");
    }

    /**
     * <p>Bonus calculation with subordinate: employee</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>bonus for each subordinate chief of all levels: 1%</p>
     * <p>bonus for all subordinates of all levels: 0,3%</p>
     */
    @Test
    void getBonusForEmployee() {
        log.info("Start test: Bonus calculation with subordinate: employee");
        findDate = LocalDate.of(2015, 2, 14);
        Employee employee = getEmployee(startWork);
        double employeeSalary = employee.getSalary(findDate);
        assertEquals(employeeSalary, 130);

        employees.add(employee);
        sales.setChildEmployee(employees);

        double salesBonus = sales.getBonus(findDate);
        assertEquals(salesBonus, 0.39);
        log.info(String.format("Result: %s equals 0.39", salesBonus));
        log.info("End test: Bonus calculation with subordinate: employee");
    }

    /**
     * <p>Bonus calculation with subordinate: manager</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>bonus for each subordinate chief of all levels: 1%</p>
     * <p>bonus for all subordinates of all levels: 0,3%</p>
     */
    @Test
    void getBonusForManager() {
        log.info("Start test: Bonus calculation with subordinate: manager");
        findDate = LocalDate.of(2015, 2, 14);
        Manager manager = getManager(startWork, emptyList());
        double employeeSalary = manager.getSalary(findDate);
        assertEquals(employeeSalary, 140);

        sales.setChildEmployee(singletonList(manager));

        double salesBonus = sales.getBonus(findDate);
        assertEquals(salesBonus, 0.42);
        log.info(String.format("Result: %s equals 0.42", salesBonus));
        log.info("End test: Bonus calculation with subordinate: manager");
    }

    /**
     * <p>Bonus calculation with subordinate: manager</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>bonus for each subordinate chief of all levels: 1%</p>
     * <p>bonus for all subordinates of all levels: 0,3%</p>
     */
    @Test
    void getBonusForSales() {
        log.info("Start test: Bonus calculation with subordinate: sales");
        findDate = LocalDate.of(2015, 2, 14);
        Sales childrenSales = getSales(startWork, emptyList());
        double employeeSalary = childrenSales.getSalary(findDate);
        assertEquals(employeeSalary, 110);

        sales.setChildEmployee(singletonList(childrenSales));

        double salesBonus = sales.getBonus(findDate);
        assertEquals(salesBonus, 0.33);
        log.info(String.format("Result: %s equals 0.42", salesBonus));
        log.info("End test: Bonus calculation with subordinate: sales");
    }

    /**
     * <p>Bonus calculation with 25 subordinates</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>bonus for each subordinate chief of all levels: 1%</p>
     * <p>bonus for all subordinates of all levels: 0,3%</p>
     */
    @Test
    void getBonusToMoreChildrenForSales() {
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
        Sales salesChief = getSales(startWork, singletonList(sales1));
        double bonus = salesChief.getBonus(findDate);
        assertEquals(bonus, 1.3385301559046772);
        log.info(String.format("Result: %s equals 1.3385301559046772", bonus));
        log.info("End test: Bonus calculation with 25 subordinates");
    }

    /**
     * <p>Salary calculation with 25 subordinates</p>
     * <p>term of work leader: 10 years</p>
     * <p>term of work employee: 10 years</p>
     * <p>base rate: 100</p>
     * <p>bonus for each subordinate chief of all levels: 1%</p>
     * <p>bonus for all subordinates of all levels: 0,3%</p>
     */
    @Test
    void getSalaryToMoreChildrenForSales() {
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
        Sales salesChief = getSales(startWork, singletonList(sales1));
        double salary = salesChief.getSalary(findDate);
        assertEquals(salary, 112.67706031180936);
        log.info(String.format("Result: %s equals 112.67706031180936", salary));
        log.info("End test: Salary calculation with 25 subordinates");
    }
}
