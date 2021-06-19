package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class Manager extends Employee {

    public Manager() {
        super(0.05, 0.4);
    }

    public Manager(double salaryIncreasePercentage, double salaryIncreaseLimitPercentage, double percentageAllSalaryFromChild) {
        super(salaryIncreasePercentage, salaryIncreaseLimitPercentage);
        this.percentageAllSalaryFromChild = percentageAllSalaryFromChild;
    }

    public Manager(double salaryIncreasePercentage, double salaryIncreaseLimitPercentage, double percentageAllSalaryFromChild, LocalDate date, List<BaseEmployee> childEmployee) {
        super(salaryIncreasePercentage, salaryIncreaseLimitPercentage, date);
        this.percentageAllSalaryFromChild = percentageAllSalaryFromChild;
        this.childEmployee = childEmployee;
    }

    public Manager(LocalDate startWorkToCompany, List<BaseEmployee> childEmployee) {
        super(0.05, 0.4, startWorkToCompany);
        this.childEmployee = childEmployee;
    }

    @Getter
    @Setter
    protected double percentageAllSalaryFromChild = 0.005;

    @Getter
    @Setter
    private List<BaseEmployee> childEmployee;

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
