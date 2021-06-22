package model;

import queue.QueueImpl;

import java.time.LocalDate;
import java.util.List;

import static utils.Const.*;

/**
 * Sales implementation
 */
public class Sales extends Manager {

    private double percentageAllSalaryChiefFromChild = PERCENTAGE_ALL_SALARY_CHIEF_FROM_CHILD;

    public Sales(LocalDate date, List<BaseEmployee> list) {
        super(SALARY_INCREASE_PERCENTAGE_FOR_SALES, SALARY_INCREASE_LIMIT_PERCENTAGE_FOR_SALES, PERCENTAGE_ALL_SALARY_FROM_CHILD_FOR_SALES, date, list);
    }

    @Override
    public boolean hasLeader() {
        return childEmployee != null && !childEmployee.isEmpty();
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
        return super.getSalary(date) + this.getBonus(date);
    }

    /**
     * Bonus calculation taking into account the salaries of all employees and leaders
     *
     * @param date specific date
     * @return bonus
     */
    @Override
    public double getBonus(LocalDate date) {
        QueueImpl<BaseEmployee> queue = new QueueImpl<>();
        queue.addAll(this.childEmployee);
        double fullSum = 0;

        while (!queue.isEmpty()) {
            BaseEmployee employee = queue.remove();

            fullSum = fullSum + employee.getSalary(date) * percentageAllSalaryFromChild;

            if (employee.hasLeader()) {
                fullSum = fullSum + employee.baseSalaryRate * percentageAllSalaryChiefFromChild;
            }

            if (!employee.getChildEmployee().isEmpty()) {
                queue.addAll(employee.getChildEmployee());
            }
        }

        return fullSum;
    }
}
