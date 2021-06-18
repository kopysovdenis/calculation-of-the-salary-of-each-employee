package model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements BaseEmployee{

    public Employee(double salaryIncreasePercentage, double salaryIncreaseLimitPercentage) {
        this.salaryIncreasePercentage = salaryIncreasePercentage;
        this.salaryIncreaseLimitPercentage = salaryIncreaseLimitPercentage;
    }

    @Getter
    @Setter
    private Date startWorkToCompany;

    @Setter
    private long workExperience;

    @Getter
    private double salaryIncreasePercentage = 0.03;

    @Getter
    private double salaryIncreaseLimitPercentage = 0.3;

    @Getter
    @Setter
    private double salaryIncreaseLimit = this.getBaseSalaryRate() * salaryIncreaseLimitPercentage;

    private double salary;

    public long getWorkExperience() {
        return (new Date().getTime() - this.startWorkToCompany.getTime())/(365L * 24 * 60 * 60 * 1000);
    }

    @Override
    public double getSalary() {
        long workExperienceYear = this.getWorkExperience();
        double increaseForYear = this.getBaseSalaryRate() * salaryIncreasePercentage;
        var increaseForWork = increaseForYear * workExperienceYear;
        if (increaseForWork > this.salaryIncreaseLimit )
            return salary + salaryIncreaseLimit;
        else
            return salary + increaseForWork;
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
    public long getBonusFromChildren() {
        return 0;
    }
    @Override
    public double getBonus() {
        return 0;
    }
}
