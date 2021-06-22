package model;

import queue.QueueImpl;

import java.time.LocalDate;
import java.util.List;

import static utils.Const.*;

public class Sales extends Manager {

    private double percentageAllSalaryChiefFromChild = PERCENTAGE_ALL_SALARY_CHIEF_FROM_CHILD;

    public Sales(LocalDate date, List<BaseEmployee> list) {
        super(SALARY_INCREASE_PERCENTAGE_FOR_SALES, SALARY_INCREASE_LIMIT_PERCENTAGE_FOR_SALES, PERCENTAGE_ALL_SALARY_FROM_CHILD_FOR_SALES, date, list);
    }

    @Override
    public boolean hasChief() {
        return childEmployee != null && !childEmployee.isEmpty();
    }

    @Override
    public double getSalary(LocalDate date) {
        return super.getSalary(date) + this.getBonus(date);
    }

    @Override
    public double getBonus(LocalDate date) {
        QueueImpl<BaseEmployee> queue = new QueueImpl<>();
        queue.addAll(this.childEmployee);
        double fullSum = 0;

        while (!queue.isEmpty()) {
            BaseEmployee employee = queue.remove();

            fullSum = fullSum + employee.getSalary(date) * percentageAllSalaryFromChild;

            if (employee.hasChief()) {
                fullSum = fullSum + employee.baseSalaryRate * percentageAllSalaryChiefFromChild;
            }

            if (!employee.getChildEmployee().isEmpty()) {
                queue.addAll(employee.getChildEmployee());
            }
        }

        return fullSum;
    }
}
