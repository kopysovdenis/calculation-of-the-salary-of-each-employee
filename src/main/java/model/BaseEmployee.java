package model;

import java.time.LocalDate;
import java.util.List;

import static utils.Const.BASE_SALARY_RATE;

/**
 * basic set of methods for working with an employee
 */
public interface BaseEmployee {

    int baseSalaryRate = BASE_SALARY_RATE;

    /**
     * Receiving the salary for the current date
     *
     * @param date current date
     * @return current salary
     */
    double getSalary(LocalDate date);

    /**
     * Сurrent bonus from subordinates
     *
     * @param date current date
     * @return current bonus
     */
    double getBonus(LocalDate date);

    /**
     * is the leader?
     */
    boolean hasLeader();

    /**
     * List of employees in submission
     *
     * @return List<BaseEmployee>
     */
    List<BaseEmployee> getChildEmployee();
}
