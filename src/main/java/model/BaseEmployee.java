package model;

import java.time.LocalDate;
import java.util.List;

import static utils.Const.BASE_SALARY_RATE;

public interface BaseEmployee {

    int baseSalaryRate = BASE_SALARY_RATE;

    double getSalary(LocalDate date);

    double getBonus(LocalDate date);

    boolean hasChief();

    List<BaseEmployee> getChildEmployee();
}
