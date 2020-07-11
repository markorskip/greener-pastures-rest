package io.greenerpastures.rest.bestopportunity.service;

import io.greenerpastures.rest.bestopportunity.model.OpportunityResults;
import io.greenerpastures.rest.bestopportunity.model.OpportunityInputs;
import io.greenerpastures.rest.bestopportunity.model.StateCostOfLiving;
import io.greenerpastures.rest.bestopportunity.repostitory.StateCostOfLivingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class BestOpportunityService {

    private CostOfLivingService costOfLivingService;

    @Autowired
    public BestOpportunityService(CostOfLivingService costOfLivingService) {
        this.costOfLivingService = costOfLivingService;
    }


    // TODO - study the design of controllers, do they compose of other services, should these all be combined into one
    // service? Or should the controller contact the multiples
    public OpportunityResults calculate(OpportunityInputs inputs) {

        OpportunityResults results = new OpportunityResults(inputs);

        // First calculate taxes owed,
        results.setAnnualTaxesOwed(TaxesOwedService.calculateAnnualTaxesOwed(inputs));

        // Second calculate Cost of Living
        results.setStateCostOfLiving(costOfLivingService.calculateCostOfLivingBasedOnStateIntitials(inputs.getState()));

        return results;
    }

    private BigDecimal computeAdjustedPay(BigDecimal afterTaxPay, BigDecimal valueOfADollar) {
        return afterTaxPay.multiply(valueOfADollar);
    }


}
