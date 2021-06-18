package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Manager extends Employee {

    public Manager() {
        super(0.05, 0.4);
    }

    public Manager(double salaryIncreasePercentage, double salaryIncreaseLimitPercentage, double percentageAllSalaryFromChild) {
        super(salaryIncreasePercentage, salaryIncreaseLimitPercentage);
        this.percentageAllSalaryFromChild = percentageAllSalaryFromChild;
    }

    @Getter
    @Setter
    private double percentageAllSalaryFromChild = 0.005;

    @Getter
    @Setter
    private List<BaseEmployee> childEmployee;

    @Override
    public boolean hasChief() {
        return !childEmployee.isEmpty();
    }

    @Override
    public double getSalary() {
        return super.getSalary() + getBonus();
    }

    @Override
    public double getBonus() {
        return childEmployee.stream()
                .mapToDouble(BaseEmployee::getSalary)
                .sum() * percentageAllSalaryFromChild;
    }
}
