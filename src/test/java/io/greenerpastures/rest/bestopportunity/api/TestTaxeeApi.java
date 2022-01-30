package io.greenerpastures.rest.bestopportunity.api;

import io.greenerpastures.rest.bestopportunity.model.taxeeResponse.TaxeeResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

public class TestTaxeeApi {

    @Test
    public void testTaxeeApi() {
        ResponseEntity<TaxeeResponse> response = apiCall("NM","married","125000");
        System.out.println(Objects.requireNonNull(response.getBody()).getTaxesOwed());
        Assert.assertEquals(new BigDecimal("13624"), response.getBody().getTaxesOwed().getFederal());
        Assert.assertEquals(new BigDecimal("4521.4"), response.getBody().getTaxesOwed().getState());
        Assert.assertEquals(new BigDecimal("9562.5"), response.getBody().getTaxesOwed().getFica());
    }

    public ResponseEntity<TaxeeResponse> apiCall(String state, String filingStatus, String payRate) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("state",state);
        map.add("filing_status",filingStatus);
        map.add("pay_rate",payRate);

        String uri = "https://taxee.io/api/v2/calculate/2020";
        String auth = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUElfS0VZX01BTkFHRVIiLCJodHRwOi8vdGF4ZWUuaW8vdXNlcl9pZCI6IjVlMGNiMzE1MGM1ZDE5MjkxMWQzNDg1MiIsImh0dHA6Ly90YXhlZS5pby9zY29wZXMiOlsiYXBpIl0sImlhdCI6MTU3Nzg5MDU4MX0.-gjctbfrZpR0Hw3C-CavZNEGAl2-890FJSG5TSml3i0";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccessControlAllowOrigin("*");
        httpHeaders.set("Authorization", auth);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TaxeeResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                request,
                TaxeeResponse.class);

        return response;
    }
}
