package io.greenerpastures.rest.bestopportunity.model;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
public class OpportunityInputs {

    private BigDecimal salary;
    private String filingStatus; // TODO replace with ENUM
    private String state; // TODO replace with ENUM

}
