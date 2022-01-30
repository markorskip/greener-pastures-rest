package io.greenerpastures.rest.realcomp.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FringeBenefits {

    private double yearlyValueOfHealthCare;
    private double otherBenefits;
    private double officeAndEquipment;
    private double educationExpenses;
    private double other;

    public BigDecimal calculateTotalFringeValue() {
        double total = 0;
        total += yearlyValueOfHealthCare + otherBenefits +
                officeAndEquipment + educationExpenses +
                other;
        return new BigDecimal(total);
    }


    @Override
    public String toString() {
        return "FringeBenefits{" +
                "yearlyValueOfHealthCare=" + yearlyValueOfHealthCare +
                ", otherBenefits=" + otherBenefits +
                ", officeAndEquipment=" + officeAndEquipment +
                ", educationExpenses=" + educationExpenses +
                ", other=" + other +
                '}';
    }
}
