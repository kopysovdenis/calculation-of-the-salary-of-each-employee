package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stack.SimpleQueue;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class Sales extends Manager {


    @Getter
    @Setter
    private double percentageAllSalaryChiefFromChild = 0.01;

    @Getter
    @Setter
    private List<BaseEmployee> childEmployee;

    public Sales() {
        super(0.01, 0.35, 0.005);
    }

    public Sales(LocalDate date, List<BaseEmployee> list) {
        super(0.01, 0.35, 0.005, date, list);
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
        double fullSum = 0.0;

        while (!stack.isEmpty()) {
            BaseEmployee employee = stack.remove();

            fullSum = fullSum + employee.getSalary(date) * this.percentageAllSalaryFromChild;

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
