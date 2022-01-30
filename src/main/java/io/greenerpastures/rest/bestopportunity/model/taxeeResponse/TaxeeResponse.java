package io.greenerpastures.rest.bestopportunity.model.taxeeResponse;

import io.greenerpastures.rest.bestopportunity.model.Annual;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxeeResponse {

    private io.greenerpastures.rest.bestopportunity.model.taxeeResponse.Annual annual;

    public Annual getTaxesOwed() {
        Annual annual = new Annual();
        annual.setFederal(new BigDecimal(this.annual.getFederal().getAmount()));
        annual.setFica(new BigDecimal(this.annual.getFica().getAmount()));
        annual.setState(new BigDecimal(this.annual.getState().getAmount()));
        return annual;
    }
}
