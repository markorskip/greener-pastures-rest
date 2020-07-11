package io.greenerpastures.rest.bestopportunity.model.taxeeResponse;

import io.greenerpastures.rest.bestopportunity.model.AnnualTaxesOwed;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TaxeeResponse implements Serializable {

    private Annual annual;

    public AnnualTaxesOwed getTaxesOwed() {
        AnnualTaxesOwed annualTaxesOwed = new AnnualTaxesOwed();
        annualTaxesOwed.setFedTax(new BigDecimal(this.annual.getFederal().getAmount()));
        annualTaxesOwed.setFicaTax(new BigDecimal(this.annual.getFica().getAmount()));
        annualTaxesOwed.setStateTax(new BigDecimal(this.annual.getState().getAmount()));
        return annualTaxesOwed;
    }
}
