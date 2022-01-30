package io.greenerpastures.rest.realcomp;

import io.greenerpastures.rest.realcomp.model.FringeBenefits;
import io.greenerpastures.rest.realcomp.model.PaidDaysOff;
import io.greenerpastures.rest.realcomp.model.RealCompensation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class RealCompensationTest {

    RealCompensation createTestData() {
        PaidDaysOff paidDaysOff = new PaidDaysOff();
        paidDaysOff.setPaidHolidays(10);
        paidDaysOff.setSickDays(10);
        paidDaysOff.setVacationDays(15);
        paidDaysOff.setPaidSnowDays(2);

        FringeBenefits fringeBenefits = new FringeBenefits();
        fringeBenefits.setOtherBenefits(2400);
        fringeBenefits.setYearlyValueOfHealthCare(15000);
        fringeBenefits.setOfficeAndEquipment(5000);
        fringeBenefits.setEducationExpenses(5000);


        RealCompensation realCompensation = new RealCompensation(112000, 11000, paidDaysOff, fringeBenefits);
        return realCompensation;
    }

    @Test
    public void testCalculateSalary() {
        Assert.assertEquals(112000, createTestData().calculateSalary().intValue());
    }

    @Test
    public void testCalculateHourlyWage() {
        Assert.assertEquals(89.10, createTestData().calculateRealHourlyWage(),0.01);
    }

    @Test
    public void testEmployerCosts() {
        RealCompensation testData = createTestData();
        Assert.assertEquals(8568,testData.calculateEmployerCosts().doubleValue(),.001);
    }

    @Test
    public void testCalculateRealCompensation() {
        RealCompensation testData = createTestData();
        int expectedAcc = 0;
        expectedAcc += testData.getFringeBenefits().calculateTotalFringeValue().intValue();
        expectedAcc += testData.getMatch401k().intValue();
        expectedAcc += testData.getYearlySalary().intValue();
        expectedAcc += testData.calculateEmployerCosts().intValue();
        Assert.assertEquals(expectedAcc, createTestData().calculateTotalCompensation().intValue());
    }

    @Test
    public void testCalculateRealHourlyWage() {
        // Real hourly wage out to be real compensation divided by actual hours works
        RealCompensation testData = createTestData();
        int realComp = testData.calculateTotalCompensation().intValue();
        int hoursWorkInAYear = testData.getPaidDaysOff().calculateHoursWorkedInAYear();
        Assert.assertEquals(realComp / hoursWorkInAYear,testData.calculateRealHourlyWage(),0001);
    }
}


