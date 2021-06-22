package model;

import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static utils.Const.*;

/**
 * Manager implementation
 */
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

    /**
     * Percentage of the salary of all subordinates of the first level
     */
    protected double percentageAllSalaryFromChild = PERCENTAGE_ALL_SALARY_FROM_CHILD_FOR_MANAGER;

    /**
     * list of subordinates
     */
    @Setter
    protected List<BaseEmployee> childEmployee;

    @Override
    public boolean hasLeader() {
        return !childEmployee.isEmpty();
    }

    /**
     * <p>Receiving salary on the specified date</p>
     * <p>The salary is formed from the main part and the bonus</p>
     *
     * @param date specific date
     * @return salary
     */
    @Override
    public double getSalary(LocalDate date) {
        return super.getSalary(date) + getBonus(date);
    }

    /**
     * Bonus calculation taking into account the salaries of all first-level employees
     *
     * @param date specific date
     * @return bonus
     */
    @Override
    public double getBonus(LocalDate date) {
        double fullSum = 0;

        for (BaseEmployee employee : childEmployee) {
            fullSum = fullSum + employee.getSalary(date);
        }

        return fullSum * percentageAllSalaryFromChild;
    }
}
