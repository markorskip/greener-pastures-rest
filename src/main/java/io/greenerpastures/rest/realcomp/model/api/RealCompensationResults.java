package io.greenerpastures.rest.realcomp.model.api;

import java.math.BigDecimal;

public class RealCompensationResults {

    private BigDecimal salary;
    private BigDecimal currentHourlyBasedOnSalaryOnly;
    private BigDecimal employerMatch401k;
    private BigDecimal employerPaidFICA;
    private BigDecimal benefitsValue;
    private int workingHoursAfterTimeOff;
    private BigDecimal totalRealCompensation;
    private BigDecimal realHourlyPay; // totalRealCompensation diveded by actual working hours
}
