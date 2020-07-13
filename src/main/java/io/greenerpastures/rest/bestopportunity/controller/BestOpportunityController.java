package io.greenerpastures.rest.bestopportunity.controller;

import io.greenerpastures.rest.bestopportunity.service.BestOpportunityService;
import io.greenerpastures.rest.bestopportunity.model.OpportunityInputs;
import io.greenerpastures.rest.bestopportunity.model.OpportunityResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BestOpportunityController {

    private final BestOpportunityService bestOpportunityService;

    @Autowired
    public BestOpportunityController(BestOpportunityService bestOpportunityService) {
        this.bestOpportunityService = bestOpportunityService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> calculateStatus() {
        return ResponseEntity.ok("Best Opportunity API running...");
    }

    @PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OpportunityResults> calculate(@RequestBody OpportunityInputs inputs) {
        return ResponseEntity.ok(bestOpportunityService.calculate(inputs));
    }
}
