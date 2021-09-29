package aws.json_api_gateway_caller;

import aws.json_api_gateway_caller.JsonApiGatewayCaller;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.http.HttpMethodName;
import json.JsonParser;
import json.model.cost_and_usages.CostAndUsagesJson;
import security.CredentialsClient;


import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class Runner {

    private final String AWS_REGION = "us-east-1";
    private final String AWS_API_GATEWAY_ENPOINT = "https://ce.us-east-1.amazonaws.com";



    private final String TestRequest2 ="{\n" +
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

    public Runner() {

        System.out.println(TestRequest2);
    }

    public CostAndUsagesJson MakeRequest() {


        try {


            JsonApiGatewayCaller caller = new JsonApiGatewayCaller(
                    null,
                    AWS_REGION,
                    new URI(AWS_API_GATEWAY_ENPOINT)
            );


            ApiGatewayResponse response = caller.execute(HttpMethodName.POST, "", new ByteArrayInputStream(TestRequest2.getBytes()));

            System.out.println(response.getBody());

            JsonParser jsonParser = new JsonParser();

           return jsonParser.ParseCostAndUsageString(response.getBody());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return new CostAndUsagesJson();
    }


}
