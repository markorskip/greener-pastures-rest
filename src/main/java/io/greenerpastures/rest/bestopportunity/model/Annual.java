package io.greenerpastures.rest.bestopportunity.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Annual {

    private BigDecimal federal = new BigDecimal(0);
    private BigDecimal state = new BigDecimal(0);
    private BigDecimal fica = new BigDecimal(0);

    public BigDecimal getTotalTax() {
        return this.federal.add(this.state.add(this.fica));
    }

}
