package io.greenerpastures.rest.bestopportunity.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.greenerpastures.rest.bestopportunity.model.StateCostOfLiving;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;

class CostOfLivingServiceTest {

    @Test
    public void it_should_create_an_in_memory_cost_of_living() throws JsonProcessingException {
        CostOfLivingService costOfLivingService = new CostOfLivingService();
        int size = costOfLivingService.getInMemoryCostOfLiving().size();
        System.out.println(size);
        Assert.isTrue(size == 51, "Should contain 50 states and DC");
    }

    @Test
    public void it_should_return_state_cost_of_living() throws JsonProcessingException {
        CostOfLivingService costOfLivingService = new CostOfLivingService();
        StateCostOfLiving stateCostOfLiving = costOfLivingService.calculate("FL");
        Assert.notNull(stateCostOfLiving, "State cost of living should not be empty");
        System.out.println(stateCostOfLiving);
        Assert.isTrue(
                stateCostOfLiving.getValueOfADollar().equals(new BigDecimal("1.00")),
                "Florida should be one dollar");
    }


}
