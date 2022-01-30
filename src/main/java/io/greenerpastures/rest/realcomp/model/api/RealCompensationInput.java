package io.greenerpastures.rest.realcomp.model.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RealCompensationInput {

    private BigDecimal yearlySalary;
    private BigDecimal employerMatch401kAmount;
    private double employerMatch401kPercentage;
    private int daysOfVacationAndSick;
    private int daysOfVacation;
    private int daysOfSick;
    private int numberOfHolidays;
    private BigDecimal yearlyValueOfHealthcare;
    private BigDecimal yearlyValueOtherBenefits;
    private BigDecimal yearlyValueOfficeAndEquipment;
    private BigDecimal yearlyValueEducationExpenses;
    private BigDecimal yearlyValueOtherFringeBenefits;

}
