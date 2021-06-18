package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import stack.SimpleQueue;

import java.util.List;

@Builder
@AllArgsConstructor
public class Sales extends Employee {

    public Sales() {
        super(0.01, 0.35);
    }

    @Getter
    @Setter
    private double percentageAllSalaryFromChild = 0.005;

    @Getter
    @Setter
    private double percentageAllSalaryChiefFromChild = 0.01;

    @Getter
    @Setter
    private List<BaseEmployee> childEmployee;

    @Override
    public boolean hasChief() {
        return !childEmployee.isEmpty();
    }

    @Override
    public double getSalary() {
        double salary = super.getSalary();

        return salary;
    }

    @Override
    public double getBonus() {
        SimpleQueue<BaseEmployee> stack = new SimpleQueue<>();
        stack.addAll(this.childEmployee);
        double fullSum = 0.0;


        while (!stack.isEmpty()) {
            BaseEmployee employee = stack.remove();

            fullSum = fullSum + employee.getSalary() * percentageAllSalaryFromChild;

            if (employee.hasChief()) {
                fullSum = fullSum + employee.getBaseSalaryRate()*percentageAllSalaryChiefFromChild;
            }

            if (!employee.getChildEmployee().isEmpty()) {
                stack.addAll(employee.getChildEmployee());
            }
        }

        return fullSum;
    }
}
