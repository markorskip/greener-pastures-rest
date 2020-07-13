package io.greenerpastures.rest.bestopportunity.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.greenerpastures.rest.bestopportunity.model.StateCostOfLiving;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

@Component
public class CostOfLivingService {

    private final HashMap<String, StateCostOfLiving> inMemoryCostOfLiving = new HashMap<>();

    public HashMap<String, StateCostOfLiving> getInMemoryCostOfLiving() {
        return inMemoryCostOfLiving;
    }

    public CostOfLivingService() throws JsonProcessingException {
        initCostOfLiving();
    }

    // TODO make state initials an ENUM
    public StateCostOfLiving calculate(String stateInitials) {
        StateCostOfLiving stateCostOfLiving = inMemoryCostOfLiving.get(stateInitials);
        if (stateCostOfLiving == null) {
            System.out.println("State not found");
        }
        return stateCostOfLiving;
    }


    private void initCostOfLiving() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectReader reader = mapper.reader();
        JsonNode node = reader.readTree(costOfLivingJsonString);

        Iterator<String> fieldNames = node.fieldNames();

        while(fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode field = node.get(fieldName);
            StateCostOfLiving stateCostOfLiving = new StateCostOfLiving();
            stateCostOfLiving.setInitials(fieldName);
            stateCostOfLiving.setValueOfADollar(cleanNumber(field.get("dollar")));
            stateCostOfLiving.setAverageRent(cleanNumber(field.get("rent")));
            inMemoryCostOfLiving.put(fieldName, stateCostOfLiving);
        }
    }

    static BigDecimal cleanNumber(JsonNode string) {
        return new BigDecimal(string.toString().replace("$","").replace("\"","").replace(",",""));
    }


    private final String costOfLivingJsonString = "{\n" +
            "    \"AL\": { \"dollar\": \"1.1\",  \"rent\": \"$998\"},\n" +
            "    \"AK\": { \"dollar\": \"0.9\", \"rent\": \"$1,748\"},\n" +
            "    \"AZ\": { \"dollar\": \"1.0\", \"rent\": \"$1,356\"},\n" +
            "    \"AR\": { \"dollar\": \"1.1\", \"rent\": \"$953\"},\n" +
            "    \"CA\": { \"dollar\": \"0.8\", \"rent\": \"$2,518\"},\n" +
            "    \"CO\": { \"dollar\": \"0.97\", \"rent\": \"$1,927\"},\n" +
            "    \"CT\": { \"dollar\": \"0.92\", \"rent\": \"$1,803\"},\n" +
            "    \"DE\": { \"dollar\": \"1.00\", \"rent\": \"$1,435\"},\n" +
            "    \"FL\": { \"dollar\": \"1.00\", \"rent\": \"$1,590\"},\n" +
            "    \"GA\": { \"dollar\": \"1.09\", \"rent\": \"$1,262\"},\n" +
            "    \"HI\": { \"dollar\": \"0.84\", \"rent\": \"$2,481\"},\n" +
            "    \"ID\": { \"dollar\": \"1.08\", \"rent\": \"$1,238\"},\n" +
            "    \"IL\": { \"dollar\": \"1.01\", \"rent\": \"$1,463\"},\n" +
            "    \"IN\": { \"dollar\": \"1.11\", \"rent\": \"$1,113\"},\n" +
            "    \"IA\": { \"dollar\": \"1.11\", \"rent\": \"$1,057\"},\n" +
            "    \"KS\": { \"dollar\": \"1.10\", \"rent\": \"$1,051\"},\n" +
            "    \"KY\": { \"dollar\": \"1.14\", \"rent\": \"$1,084\"},\n" +
            "    \"LA\": { \"dollar\": \"1.11\", \"rent\": \"$1,245\"},\n" +
            "    \"ME\": { \"dollar\": \"1.02\", \"rent\": \"$1,466\"},\n" +
            "    \"MD\": { \"dollar\": \"0.91\", \"rent\": \"$1,807\"},\n" +
            "    \"MA\": { \"dollar\": \"0.93\", \"rent\": \"$2,252\"},\n" +
            "    \"MI\": { \"dollar\": \"1.07\", \"rent\": \"$1,110\"},\n" +
            "    \"MN\": { \"dollar\": \"1.03\", \"rent\": \"$1,449\"},\n" +
            "    \"MS\": { \"dollar\": \"1.16\", \"rent\": \"$1,055\"},\n" +
            "    \"MO\": { \"dollar\": \"1.12\", \"rent\": \"$1,047\"},\n" +
            "    \"MT\": { \"dollar\": \"1.06\", \"rent\": \"$1,234\"},\n" +
            "    \"NE\": { \"dollar\": \"1.10\", \"rent\": \"$1,253\"},\n" +
            "    \"NV\": { \"dollar\": \"1.03\", \"rent\": \"$1,423\"},\n" +
            "    \"NH\": { \"dollar\": \"0.94\", \"rent\": \"$1,748\"},\n" +
            "    \"NJ\": { \"dollar\": \"0.88\", \"rent\": \"$2,062\"},\n" +
            "    \"NM\": { \"dollar\": \"1.07\", \"rent\": \"$1,200\"},\n" +
            "    \"NY\": { \"dollar\": \"0.87\", \"rent\": \"$2,050\"},\n" +
            "    \"NC\": { \"dollar\": \"1.10\", \"rent\": \"$1,208\"},\n" +
            "    \"ND\": { \"dollar\": \"1.09\", \"rent\": \"$1,290\"},\n" +
            "    \"OH\": { \"dollar\": \"1.12\", \"rent\": \"$1,113\"},\n" +
            "    \"OK\": { \"dollar\": \"1.12\", \"rent\": \"$950\"},\n" +
            "    \"OR\": { \"dollar\": \"1.00\", \"rent\": \"$1,707\"},\n" +
            "    \"PA\": { \"dollar\": \"1.02\", \"rent\": \"$1,242\"},\n" +
            "    \"RI\": { \"dollar\": \"1.00\", \"rent\": \"$1,725\"},\n" +
            "    \"SC\": { \"dollar\": \"1.11\", \"rent\": \"$1,209\"},\n" +
            "    \"SD\": { \"dollar\": \"1.13\", \"rent\": \"$1,213\"},\n" +
            "    \"TN\": { \"dollar\": \"1.11\", \"rent\": \"$1,153\"},\n" +
            "    \"TX\": { \"dollar\": \"1.03\", \"rent\": \"$1,455\"},\n" +
            "    \"UT\": { \"dollar\": \"1.03\", \"rent\": \"$1,526\"},\n" +
            "    \"VT\": { \"dollar\": \"0.98\", \"rent\": \"$1,599\"},\n" +
            "    \"VA\": { \"dollar\": \"0.98\", \"rent\": \"$1,452\"},\n" +
            "    \"WA\": { \"dollar\": \"0.95\", \"rent\": \"$1,838\"},\n" +
            "    \"WV\": { \"dollar\": \"1.14\", \"rent\": \"$888\"},\n" +
            "    \"WI\": { \"dollar\": \"1.08\", \"rent\": \"$1,141\"},\n" +
            "    \"WY\": {\"dollar\": \"1.03\", \"rent\": \"$1,149\"},\n" +
            "    \"DC\": {\"dollar\": \"0.84\", \"rent\": \"$2339\"}\n" +
            "}";

}
