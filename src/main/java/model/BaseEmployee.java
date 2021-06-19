package model;

import java.time.LocalDate;
import java.util.List;

public interface BaseEmployee {

    int baseSalaryRate = 10;

    default int getBaseSalaryRate() {
        return baseSalaryRate;
    }

    double getSalary(LocalDate date);

    double getBonus(LocalDate date);

    boolean hasChief();

    List<BaseEmployee> getChildEmployee();
}
