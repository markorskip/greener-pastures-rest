package io.greenerpastures.rest.bestopportunity.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class CostOfLivingCalculatorTest {

    private static final String PROD_URL = "https://greenpastures.herokuapp.com/calculate";

    ObjectMapper mapper = new ObjectMapper();
    JSONObject inputs;

    @Test
    public void testCostOfLivingCalculator() throws JsonProcessingException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        inputs = new JSONObject();
        inputs.put("state", "NM");
        inputs.put("filing_status", "married");
        inputs.put("pay_rate",110000);

        HttpEntity<String> request =
                new HttpEntity<String>(inputs.toString(), headers);

        String response =
                restTemplate.postForObject(PROD_URL, request, String.class);

        JsonNode root = mapper.readTree(response);

        assertNotNull(response);
        assertNotNull(root);
        assertNotNull(root.path("annual").asText());
        assertEquals(root.path("stateCostOfLiving").path("valueOfADollar").toString(), "1.07");
        assertEquals(root.path("annual").path("totalTax").toString(), "22525.4");
        assertEquals(root.path("adjustedPay").toString(), "93597.822");
    }
}
