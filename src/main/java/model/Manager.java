package model;

import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static utils.Const.*;

public class Manager extends Employee {

    public Manager(double salaryIncreasePercentage, double salaryIncreaseLimitPercentage, double percentageAllSalaryFromChild, LocalDate date, List<BaseEmployee> childEmployee) {
        super(salaryIncreasePercentage, salaryIncreaseLimitPercentage, date);
        this.percentageAllSalaryFromChild = percentageAllSalaryFromChild;
        this.childEmployee = childEmployee;
    }

    public Manager(LocalDate startWorkToCompany, List<BaseEmployee> childEmployee) {
        super(SALARY_INCREASE_PERCENTAGE_FOR_MANAGER, SALARY_INCREASE_LIMIT_PERCENTAGE_FOR_MANAGER, startWorkToCompany);
        this.childEmployee = childEmployee;
    }

    protected double percentageAllSalaryFromChild = PERCENTAGE_ALL_SALARY_FROM_CHILD_FOR_MANAGER;

    @Setter
    protected List<BaseEmployee> childEmployee;

    @Override
    public boolean hasChief() {
        return !childEmployee.isEmpty();
    }

    @Override
    public double getSalary(LocalDate date) {
        return super.getSalary(date) + getBonus(date);
    }

    @Override
    public double getBonus(LocalDate date) {
        double fullSum = 0;

        for (BaseEmployee employee : childEmployee) {
            fullSum = fullSum + employee.getSalary(date);
        }

        return fullSum * percentageAllSalaryFromChild;
    }
}
