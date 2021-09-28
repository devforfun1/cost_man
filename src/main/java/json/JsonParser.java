package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.model.cost_and_usages.CostAndUsagesJson;

import java.util.List;

public class JsonParser {

    public JsonParser() {
    }

    public CostAndUsagesJson ParseCostAndUsageString(String jsonResponse){

        CostAndUsagesJson costAndUsages = null;

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            costAndUsages = objectMapper.readValue(jsonResponse, new TypeReference<CostAndUsagesJson>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

       costAndUsages.getResultsByTime().forEach(r -> r.getGroups().forEach(g -> System.out.println(g.getKeys().toString() +g.getMetrics().getUnblendedCost().getAmount().toString())));

        return null;
    }
}
