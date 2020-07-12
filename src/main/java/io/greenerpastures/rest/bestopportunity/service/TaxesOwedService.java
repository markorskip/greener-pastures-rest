package io.greenerpastures.rest.bestopportunity.service;

import io.greenerpastures.rest.bestopportunity.model.AnnualTaxesOwed;
import io.greenerpastures.rest.bestopportunity.model.OpportunityInputs;
import io.greenerpastures.rest.bestopportunity.model.taxeeResponse.TaxeeResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


public class TaxesOwedService {

    private HashMap<String, String> localCache;

    public AnnualTaxesOwed calculateAnnualTaxesOwed(OpportunityInputs inputs) {
        String uri = "https://taxee.io/api/v2/calculate/2020";
        String auth = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUElfS0VZX01BTkFHRVIiLCJodHRwOi8vdGF4ZWUuaW8vdXNlcl9pZCI6IjVlMGNiMzE1MGM1ZDE5MjkxMWQzNDg1MiIsImh0dHA6Ly90YXhlZS5pby9zY29wZXMiOlsiYXBpIl0sImlhdCI6MTU3Nzg5MDU4MX0.-gjctbfrZpR0Hw3C-CavZNEGAl2-890FJSG5TSml3i0";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccessControlAllowOrigin("*");
        httpHeaders.set("Authorization", auth);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("state", inputs.getState());
        map.add("filing_status", inputs.getFilingStatus());
        map.add("pay_rate", inputs.getSalary().toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<TaxeeResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                request,
                TaxeeResponse.class);

        return response.getBody().getTaxesOwed();
    }


}
