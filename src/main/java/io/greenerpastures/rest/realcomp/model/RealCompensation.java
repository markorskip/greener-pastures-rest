package io.greenerpastures.rest.realcomp.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RealCompensation {

    private BigDecimal yearlySalary;
    private BigDecimal match401k;
    private PaidDaysOff paidDaysOff;
    private FringeBenefits fringeBenefits;

    public RealCompensation() {
    }

    public RealCompensation(BigDecimal yearlySalary, BigDecimal match401k, PaidDaysOff paidDaysOff, FringeBenefits fringeBenefits) {
        this.yearlySalary = yearlySalary;
        this.match401k = match401k;
        this.paidDaysOff = paidDaysOff;
        this.fringeBenefits = fringeBenefits;
    }

    public RealCompensation(int yearlySalary, int match401k, PaidDaysOff paidDaysOff, FringeBenefits fringeBenefits) {
        this.yearlySalary = new BigDecimal(yearlySalary);
        this.match401k = new BigDecimal(match401k);
        this.paidDaysOff = paidDaysOff;
        this.fringeBenefits = fringeBenefits;
    }

    public BigDecimal calculateSalary() {
        return this.yearlySalary;
    }

    public BigDecimal calculateTotalCompensation() {
        return this.yearlySalary.add(this.match401k).add(this.fringeBenefits.calculateTotalFringeValue()).add(calculateEmployerFica());
    }

    public double calculateRealHourlyWage() {
        return this.calculateTotalCompensation().doubleValue() / this.paidDaysOff.calculateHoursWorkedInAYear();
    }

    private BigDecimal calculateEmployerFica() {
        return calculateEmployerSocialSecurityPortion().add(calculateEmployerMedicarePortion());
    }

    private BigDecimal calculateEmployerSocialSecurityPortion() {
        return Fica2020.SOCIAL_SECURITY_EMPLOYER_PORTION.calculate(yearlySalary);
    }

    private BigDecimal calculateEmployerMedicarePortion() {
        return Fica2020.MEDICARE_EMPLOYER_PORTION.calculate(yearlySalary);
    }

    public JsonNode calculateEmployerCosts() {
        // TODO implement this
        return null;
    }


//    computeBenefits() {
//        return this.accumulate('benefit')
//    },
//    employerSocialSecurity() {
//        let ficaSalary = this.salary;
//        if (this.salary > 137700) ficaSalary = 137700;
//        return ficaSalary * .062;
//    },


//    employerMedicare() {
//        return this.salary * .0145;
//    },
//    employerFica() {
//        return this.employerMedicare + this.employerSocialSecurity
//    },
//    expenses() {
//        return this.accumulate('expense')
//    },
//    currentHourly() {
//        return this.salary / 2080;
//    },
//    calculateTotalPaidDays() {
//        return this.accumulate('paid_day')
//    },
//    comp() {
//        return this.accumulate('comp')
//    },
//    totalCompensation() {
//        return this.salary + this.employerFica + this.computeBenefits + this.expenses + this.comp;
//    },
//    workingHours() {
//        let hours = this.calculateTotalPaidDays * 8;
//        return 2080 - hours;
//    },
//    realHourlyPay() {
//        return this.totalCompensation / this.workingHours;
//    }
}
