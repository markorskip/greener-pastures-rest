package io.greenerpastures.rest.bestopportunity.service;

import io.greenerpastures.rest.bestopportunity.model.StateCostOfLiving;
import io.greenerpastures.rest.bestopportunity.repostitory.StateCostOfLivingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class CostOfLivingService {

    private final StateCostOfLivingRepository repository;

    @Autowired
    public CostOfLivingService(StateCostOfLivingRepository repository) {
        this.repository = repository;
    }

    public StateCostOfLiving calculateCostOfLivingBasedOnStateIntitials(String stateInitials) {
        Optional<StateCostOfLiving> optionalStateCostOfLiving = repository.findById(stateInitials);
        StateCostOfLiving stateCostOfLiving;
        if (!optionalStateCostOfLiving.isPresent()) { // TODO remove this mock code
            stateCostOfLiving = new StateCostOfLiving();
            stateCostOfLiving.setAverageRent(new BigDecimal(1000));
            stateCostOfLiving.setInitials("TEST");
            stateCostOfLiving.setValueOfADollar(new BigDecimal(1));
            return stateCostOfLiving;
        } else { return optionalStateCostOfLiving.get(); }
    }
}
