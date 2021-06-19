package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Employee implements BaseEmployee {

    public Employee(double salaryIncreasePercentage, double salaryIncreaseLimitPercentage) {
        this.salaryIncreasePercentage = salaryIncreasePercentage;
        this.salaryIncreaseLimitPercentage = salaryIncreaseLimitPercentage;
    }

    public Employee(double salaryIncreasePercentage, double salaryIncreaseLimitPercentage, LocalDate startWorkToCompany) {
        this.salaryIncreasePercentage = salaryIncreasePercentage;
        this.salaryIncreaseLimitPercentage = salaryIncreaseLimitPercentage;
        this.startWorkToCompany = startWorkToCompany;
    }

    public Employee(LocalDate startWorkToCompany) {
        this.startWorkToCompany = startWorkToCompany;
    }


    @Getter
    @Setter
    private LocalDate startWorkToCompany;

    @Setter
    private long workExperience;

    @Getter
    private double salaryIncreasePercentage = 0.03;

    @Getter
    private double salaryIncreaseLimitPercentage = 0.3;

    @Getter
    @Setter
    private double salaryIncreaseLimit = this.getBaseSalaryRate() * salaryIncreaseLimitPercentage;


    public long getWorkExperience(LocalDate date) {
        return ChronoUnit.YEARS.between(startWorkToCompany, date);
    }

    @Override
    public double getSalary(LocalDate date) {
        long workExperienceYear = this.getWorkExperience(date);
        if (workExperienceYear < 0) {
            throw new IllegalArgumentException("The requested date cannot be less than the start date");
        }
        double increaseForYear = baseSalaryRate * salaryIncreasePercentage;
        var increaseForWork = increaseForYear * workExperienceYear;
        if (increaseForWork > this.salaryIncreaseLimit)
            return baseSalaryRate + salaryIncreaseLimit;
        else
            return baseSalaryRate + increaseForWork;
    }

    @Override
    public boolean hasChief() {
        return false;
    }

    @Override
    public List<BaseEmployee> getChildEmployee() {
        return null;
    }

    @Override
    public double getBonus(LocalDate date) {
        return 0;
    }
}
