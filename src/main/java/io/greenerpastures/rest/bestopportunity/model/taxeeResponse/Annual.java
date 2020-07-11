package io.greenerpastures.rest.bestopportunity.model.taxeeResponse;

import lombok.Data;

import java.io.Serializable;

@Data
public class Annual implements Serializable {

    private Fica fica;
    private FedTax federal;
    private StateTax state;

}
