package io.greenerpastures.rest.bestopportunity.controller;

import io.greenerpastures.rest.bestopportunity.service.BestOpportunityService;
import io.greenerpastures.rest.bestopportunity.model.OpportunityInputs;
import io.greenerpastures.rest.bestopportunity.model.OpportunityResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BestOpportunityController {

    private BestOpportunityService bestOpportunityService;

    @Autowired
    public BestOpportunityController(BestOpportunityService bestOpportunityService) {
        this.bestOpportunityService = bestOpportunityService;
    }

    @PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OpportunityResults> calculate(@RequestBody OpportunityInputs inputs) {
        return ResponseEntity.ok(bestOpportunityService.calculate(inputs));
    }
}
