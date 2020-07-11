package io.greenerpastures.rest.bestopportunity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.greenerpastures.rest.bestopportunity.model.AnnualTaxesOwed;
import io.greenerpastures.rest.bestopportunity.model.OpportunityInputs;
import io.greenerpastures.rest.bestopportunity.model.OpportunityResults;
import io.greenerpastures.rest.bestopportunity.model.StateCostOfLiving;
import io.greenerpastures.rest.bestopportunity.service.BestOpportunityService;
import io.greenerpastures.rest.bestopportunity.service.TaxesOwedService;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BestOpportunityController.class)
class BestOpportunityControllerTest {

    @MockBean
    BestOpportunityService bestOpportunityService;

    ObjectMapper mapper = new ObjectMapper();

    EasyRandom easyRandom = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_return_results_when_given_inputs() throws Exception {

        OpportunityResults results = createTestResults();
        String stateInitials = "NM";
        results.getStateCostOfLiving().setInitials(stateInitials);

        when(bestOpportunityService.calculate(any(OpportunityInputs.class))).thenReturn(results);

        mockMvc.perform(post("/calculate")
                .content(mapper.writeValueAsString(createTestInputs()))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stateCostOfLiving.initials").value(stateInitials));

    }

    public OpportunityInputs createTestInputs(){
        OpportunityInputs inputs = easyRandom.nextObject(OpportunityInputs.class);
        return inputs;
    }


    public OpportunityResults createTestResults(){
        OpportunityResults results = easyRandom.nextObject(OpportunityResults.class);
        return results;
    }
}
