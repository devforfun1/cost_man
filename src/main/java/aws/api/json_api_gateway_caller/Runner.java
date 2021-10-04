package aws.api.json_api_gateway_caller;

import com.amazonaws.http.HttpMethodName;
import json.JsonParser;
import json.model.cost_and_usages.CostAndUsagesJson;


import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class Runner {

    private final String AWS_REGION = "us-east-1";
    private final String AWS_API_GATEWAY_ENDPOINT = "https://ce.us-east-1.amazonaws.com";
    private final String costExplorerServiceName = "ce";


    private final String COST_AND_USAGE_REQUEST_BODY = "{\n" +
            "  \"TimePeriod\": {\n" +
            "    \"Start\": \"2021-09-01\",\n" +
            "    \"End\": \"2021-09-23\"\n" +
            "  },\n" +
            "  \"Granularity\": \"MONTHLY\",\n" +
            "  \"GroupBy\": [\n" +
            "    {\n" +
            "      \"Type\": \"DIMENSION\",\n" +
            "      \"Key\": \"SERVICE\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Type\": \"TAG\",\n" +
            "      \"Key\": \"Environment\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"Metrics\": [\n" +
            "    \"BlendedCost\",\n" +
            "    \"UnblendedCost\",\n" +
            "    \"UsageQuantity\"\n" +
            "  ]\n" +
            "}";


    private final String COST_AND_USAGE_WITH_RESOURCES_REQUEST_BODY = "{\n" +
            "  \"TimePeriod\": {\n" +
            "    \"Start\":\"2021-09-28\",\n" +
            "    \"End\": \"2021-10-04\"\n" +
            "  },\n" +
            "  \"Granularity\": \"DAILY\",\n" +
            " \n" +
            "  \"GroupBy\":[\n" +
            "    {\n" +
            "      \"Type\":\"DIMENSION\",\n" +
            "      \"Key\":\"RESOURCE_ID\"\n" +
            "    }\n" +
            "  ],\n" +
            "   \"Metrics\":[\"BlendedCost\", \"UnblendedCost\", \"UsageQuantity\"]\n" +
            "}\n";

    public Runner() {


    }

    public CostAndUsagesJson CostAndUsagesWithResourcesRequest() {

        final String xAMZTarget = "AWSInsightsIndexService.GetCostAndUsageWithResources";

        return MakeRequest(xAMZTarget, COST_AND_USAGE_WITH_RESOURCES_REQUEST_BODY);
    }

    public CostAndUsagesJson CostAndUsagesRequest() {

        final String xAMZTarget = "AWSInsightsIndexService.GetCostAndUsage";

        return MakeRequest(xAMZTarget, COST_AND_USAGE_REQUEST_BODY);

    }


    private CostAndUsagesJson MakeRequest(String xAMZTarget, String requestBody) {


        try {


            JsonApiGatewayCaller caller = new JsonApiGatewayCaller(
                    null,
                    AWS_REGION,
                    new URI(AWS_API_GATEWAY_ENDPOINT),
                    costExplorerServiceName,
                    xAMZTarget);


            ApiGatewayResponse response = caller.execute(HttpMethodName.POST, "",
                    new ByteArrayInputStream(requestBody.getBytes()));

            System.out.println(response.getBody());

            JsonParser jsonParser = new JsonParser();

            return jsonParser.<CostAndUsagesJson>ParseJsonString(response.getBody(), new CostAndUsagesJson());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return new CostAndUsagesJson();
    }


}
