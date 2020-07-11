package io.greenerpastures.rest.bestopportunity.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AnnualTaxesOwed {

    private BigDecimal fedTax = new BigDecimal(0);
    private BigDecimal stateTax = new BigDecimal(0);
    private BigDecimal ficaTax = new BigDecimal(0);

    public BigDecimal getTotalTax() {
        return this.fedTax.add(this.stateTax.add(this.ficaTax));
    }

}
