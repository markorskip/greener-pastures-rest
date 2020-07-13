package io.greenerpastures.rest.bestopportunity.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpportunityInputs {

    private BigDecimal pay_rate;
    private String filing_status; // TODO replace with ENUM
    private String state; // TODO replace with ENUM

}
