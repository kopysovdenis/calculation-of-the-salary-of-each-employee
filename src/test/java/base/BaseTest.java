package base;

import model.BaseEmployee;
import model.Employee;
import model.Manager;
import model.Sales;

import java.time.LocalDate;
import java.util.List;

public class BaseTest {

    public Employee getEmployee(LocalDate date) {
        return new Employee(date);
    }

    public Manager getManager(LocalDate date, List<BaseEmployee> list) {
        return new Manager(date, list);
    }

    public Sales getSales(LocalDate date, List<BaseEmployee> list) {
        return new Sales(date, list);
    }
}
