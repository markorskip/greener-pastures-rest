package io.greenerpastures.rest.realcomp.model;

import java.math.BigDecimal;

public enum Fica2020 {

    //https://www.nerdwallet.com/blog/taxes/fica-tax-withholding/
    SOCIAL_SECURITY_EMPLOYEE_PORTION {
        public BigDecimal calculate(BigDecimal earnings) {
            if (earnings.doubleValue() < 132900) return new BigDecimal(earnings.doubleValue() * .062);
            else return new BigDecimal(132900 * .062);
            }},
    SOCIAL_SECURITY_EMPLOYER_PORTION {
        public BigDecimal calculate(BigDecimal earnings) {
            if (earnings.doubleValue() < 132900) return new BigDecimal(earnings.doubleValue() * .062);
            else return new BigDecimal(132900 * .062);
    }},
    MEDICARE_EMPLOYEE_PORTION {
        public BigDecimal calculate(BigDecimal earnings) {
            double result = earnings.doubleValue() * .0145;
            if (earnings.doubleValue() > 200000) {
                double wealthTaxableAmount = earnings.doubleValue() - 200000;
                result += wealthTaxableAmount * .009;
            }
            return new BigDecimal(result);
        }},
    MEDICARE_EMPLOYER_PORTION{
        public BigDecimal calculate(BigDecimal earnings) {
            return new BigDecimal(earnings.doubleValue() * .0145);
        }};

    public abstract BigDecimal calculate(BigDecimal earnings);

}
