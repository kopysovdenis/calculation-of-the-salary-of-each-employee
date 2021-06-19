package model;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ManagerTest extends BaseTest {

    Manager manager;
    LocalDate startWork;
    LocalDate findDate;
    List<BaseEmployee> employees = new ArrayList<>();

    @BeforeEach
    void setUp() {
        startWork = LocalDate.of(2015, 2, 14);
        findDate = LocalDate.of(2016, 2, 14);
        employees.add(getEmployee(startWork));
        manager = getManager(startWork, employees);
    }

    @Test
    void getSalary() {
        System.out.println(manager.getSalary(findDate));
    }
}
