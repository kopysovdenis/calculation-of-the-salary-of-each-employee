package model;

import java.util.List;

public interface BaseEmployee {

    int baseSalaryRate = 10;

    default int getBaseSalaryRate() {
        return baseSalaryRate;
    }

    double getSalary();

    boolean hasChief();

    List<BaseEmployee> getChildEmployee();

    long getBonusFromChildren();

    double getBonus();
}
