package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static utils.Const.SALARY_INCREASE_LIMIT_PERCENTAGE_FOR_EMPLOYEE;
import static utils.Const.SALARY_INCREASE_PERCENTAGE_FOR_EMPLOYEE;

/**
 * Employee implementation
 */
public class Employee implements BaseEmployee {

    public Employee(double salaryIncreasePercentage, double salaryIncreaseLimitPercentage, LocalDate startWorkToCompany) {
        validationDate(startWorkToCompany);
        this.salaryIncreasePercentage = salaryIncreasePercentage;
        this.salaryIncreaseLimitPercentage = salaryIncreaseLimitPercentage;
        this.salaryIncreaseLimit = baseSalaryRate * salaryIncreaseLimitPercentage;
        this.startWorkToCompany = startWorkToCompany;
    }

    public Employee(LocalDate startWorkToCompany) {
        validationDate(startWorkToCompany);
        this.startWorkToCompany = startWorkToCompany;
    }

    /**
     * Date of starting work in the company
     */
    private LocalDate startWorkToCompany;

    /**
     * Percentage of wage increases per year
     */
    private double salaryIncreasePercentage = SALARY_INCREASE_PERCENTAGE_FOR_EMPLOYEE;

    /**
     * The maximum percentage of the base rate with an increase in wages
     */
    private double salaryIncreaseLimitPercentage = SALARY_INCREASE_LIMIT_PERCENTAGE_FOR_EMPLOYEE;

    /**
     * Maximum salary excluding bonus
     */
    private double salaryIncreaseLimit = baseSalaryRate * salaryIncreaseLimitPercentage;

    /**
     * Calculating the number of working years
     *
     * @param date date for calculation
     * @return number of working years
     */
    public long getWorkExperience(LocalDate date) {
        return ChronoUnit.YEARS.between(startWorkToCompany, date);
    }

    /**
     * Payroll calculation for the specified date
     *
     * @param date current specified date
     * @return salary for specific date
     */
    @Override
    public double getSalary(LocalDate date) {
        long workExperienceYear = this.getWorkExperience(date);
        if (workExperienceYear < 0) {
            throw new IllegalArgumentException("The requested date cannot be less than the start date");
        }
        double increaseForYear = baseSalaryRate * salaryIncreasePercentage;
        var increaseForWork = increaseForYear * workExperienceYear;
        if (increaseForWork > salaryIncreaseLimit)
            return baseSalaryRate + salaryIncreaseLimit;
        else
            return baseSalaryRate + increaseForWork;
    }

    /**
     * An employee cannot be a leader
     */
    @Override
    public boolean hasLeader() {
        return false;
    }

    /**
     * the employee has no subordinates
     *
     * @return empty
     */
    @Override
    public List<BaseEmployee> getChildEmployee() {
        return new ArrayList<>();
    }

    /**
     * The bonus is not credited to the employee
     *
     * @param date specific date
     */
    @Override
    public double getBonus(LocalDate date) {
        return 0;
    }

    private void validationDate(LocalDate date) {
        if (date == null)
            throw new IllegalArgumentException("the date of employment is incorrect");
    }
}
