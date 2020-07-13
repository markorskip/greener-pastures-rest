package io.greenerpastures.rest.bestopportunity.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class OpportunityResults {

    private OpportunityInputs inputs;
    private Annual annual;
    private StateCostOfLiving stateCostOfLiving;

    public OpportunityResults(OpportunityInputs inputs) {
        this.inputs = inputs;
    }

    public BigDecimal getAfterTaxPay() {
        return this.inputs.getPay_rate().subtract(annual.getTotalTax());
    }

    public BigDecimal getAdjustedPay() {
        return getAfterTaxPay().multiply(stateCostOfLiving.getValueOfADollar());
    }


}
