package io.greenerpastures.rest.bestopportunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "state_cost_of_living")
public class StateCostOfLiving {

    // TODO add last modified and source columns

    private String stateName;  // Todo create class called Location that includes state and city
    private BigDecimal averageSalary;
    private BigDecimal averageRent;
    private BigDecimal valueOfADollar;

    @Id
    private String initials;

}
