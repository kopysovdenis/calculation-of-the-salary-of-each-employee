package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stack.SimpleQueue;

import java.time.LocalDate;
import java.util.List;

import static utils.Const.*;

@AllArgsConstructor
public class Sales extends Manager {

    private double percentageAllSalaryChiefFromChild = PERCENTAGE_ALL_SALARY_CHIEF_FROM_CHILD;

    @Getter
    @Setter
    private List<BaseEmployee> childEmployee;

    public Sales() {
        super(SALARY_INCREASE_PERCENTAGE_FOR_SALES, SALARY_INCREASE_LIMIT_PERCENTAGE_FOR_SALES, PERCENTAGE_ALL_SALARY_FROM_CHILD_FOR_SALES);
    }

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
        SimpleQueue<BaseEmployee> stack = new SimpleQueue<>();
        stack.addAll(this.childEmployee);
        double fullSum = 0;

        while (!stack.isEmpty()) {
            BaseEmployee employee = stack.remove();

            fullSum = fullSum + employee.getSalary(date) * percentageAllSalaryFromChild;

            if (employee.hasChief()) {
                fullSum = fullSum + employee.getBaseSalaryRate() * percentageAllSalaryChiefFromChild;
            }

            if (!employee.getChildEmployee().isEmpty()) {
                stack.addAll(employee.getChildEmployee());
            }
        }

        return fullSum;
    }
}
