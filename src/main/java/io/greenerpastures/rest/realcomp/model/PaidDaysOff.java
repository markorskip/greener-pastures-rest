package io.greenerpastures.rest.realcomp.model;

import lombok.Data;

@Data
public class PaidDaysOff {

    private int sickDays;
    private int vacationDays;
    private int paidHolidays;
    private int paidSnowDays;
    private boolean everyOtherFridayOff = false;
    private int otherDaysOff;

    public int calculateTotalUnWorkedHoursPaid() {
        return this.calculateTotalPaidDaysOff() * 8;
    }

    public int calculateTotalPaidDaysOff() {
        return this.sickDays +
                this.vacationDays +
                this.paidHolidays +
                this.paidSnowDays;
    }

    public int calculateTotalDaysOffWorkMonThroughFriday() {
        int daysOffWork = 0;
        if (everyOtherFridayOff) {
            daysOffWork += 26;
        }
        daysOffWork += this.calculateTotalPaidDaysOff();
        return daysOffWork;
    }

    public int averageDaysInOfficePerYear() {
        return (52 * 5) - calculateTotalDaysOffWorkMonThroughFriday();
    }

    public double averageDaysAtOfficeInAMonth() {
        return ((double) (this.averageDaysInOfficePerYear())) / (double) 12;
    }

    @Override
    public String toString() {
        return "PaidDaysOff{" +
                "sickDays=" + sickDays +
                ", vacationDays=" + vacationDays +
                ", paidHolidays=" + paidHolidays +
                ", paidSnowDays=" + paidSnowDays +
                ", everyOtherFridayOff=" + everyOtherFridayOff +
                ", otherDaysOff=" + otherDaysOff +
                '}';
    }

    public int calculateHoursWorkedInAYear() {
        return 2080 - (this.calculateTotalUnWorkedHoursPaid());
    }
}
