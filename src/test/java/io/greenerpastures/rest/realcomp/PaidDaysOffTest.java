package io.greenerpastures.rest.realcomp;

import io.greenerpastures.rest.realcomp.model.PaidDaysOff;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PaidDaysOffTest {

    static PaidDaysOff createTestData() {
        PaidDaysOff paidDaysOff = new PaidDaysOff();
        paidDaysOff.setPaidHolidays(10);
        paidDaysOff.setSickDays(10);
        paidDaysOff.setVacationDays(15);
        paidDaysOff.setEveryOtherFridayOff(true);
        paidDaysOff.setPaidSnowDays(2);
        return paidDaysOff;
    }

    PaidDaysOff paidDaysOff = createTestData();

    @Test
    void testTotalDaysOff() {
        Assert.assertEquals(37,  paidDaysOff.calculateTotalPaidDaysOff());
    }

    @Test
    void testTotalUnWorkedHoursPaid() {
        Assert.assertEquals(296, paidDaysOff.calculateTotalUnWorkedHoursPaid());
    }

    @Test
    void testTotalDaysAtOffice() {
        Assert.assertEquals(197, paidDaysOff.averageDaysInOfficePerYear());
    }

    @Test
    void testTotalDaysAtOfficeInAMonth() {
        Assert.assertEquals(16.416, paidDaysOff.averageDaysAtOfficeInAMonth(), .001);
    }


}
