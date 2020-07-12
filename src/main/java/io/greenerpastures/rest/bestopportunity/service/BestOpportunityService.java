package io.greenerpastures.rest.bestopportunity.service;

import io.greenerpastures.rest.bestopportunity.model.OpportunityResults;
import io.greenerpastures.rest.bestopportunity.model.OpportunityInputs;
import io.greenerpastures.rest.bestopportunity.model.StateCostOfLiving;
import io.greenerpastures.rest.bestopportunity.repostitory.StateCostOfLivingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

@Component
public class BestOpportunityService {

    private CostOfLivingService costOfLivingService;

    private HashMap<OpportunityInputs, OpportunityResults> cache;


    @Autowired
    public BestOpportunityService(CostOfLivingService costOfLivingService) {
        this.costOfLivingService = costOfLivingService;
        this.cache = new HashMap<>();
    }


    // TODO - study the design of controllers, do they compose of other services, should these all be combined into one
    // service? Or should the controller contact the multiples
    public OpportunityResults calculate(OpportunityInputs inputs) {

        OpportunityResults cacheResults = checkCache(inputs);
        if (cacheResults != null) return cacheResults;

        OpportunityResults results = new OpportunityResults(inputs);

        // First calculate taxes owed,
        TaxesOwedService taxesOwedService = new TaxesOwedService();
        results.setAnnualTaxesOwed(taxesOwedService.calculateAnnualTaxesOwed(inputs));

        // Second calculate Cost of Living
        results.setStateCostOfLiving(costOfLivingService.calculateCostOfLivingBasedOnStateIntitials(inputs.getState()));
        addToCache(inputs, results);

        return results;
    }

    private OpportunityResults checkCache(OpportunityInputs inputs) {
        if (cache.containsKey(inputs)) {
            System.out.println("Utilizing cache for performance optimization");
            return cache.get(inputs);
        }
        return null;
    }

    private void addToCache(OpportunityInputs inputs, OpportunityResults results) {
        cache.put(inputs,results);
        System.out.println("Adding new result to cache. Size is now: " + cache.size());

        if (cache.size() > 10000) {
            System.out.println("Cache max size exceeded.  Clearing cache.");
            cache.clear();
        }
    }

}
