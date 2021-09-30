package aws.request.cost_explorer;

import annonation.AwsRequest;
import handler.response.cost_explorer.CostExplorerResponseHandler;
import aws.json_api_gateway_caller.Runner;
import base.RequestBase;
import com.amazonaws.services.costexplorer.AWSCostExplorer;
import com.amazonaws.services.costexplorer.AWSCostExplorerClientBuilder;
import com.amazonaws.services.costexplorer.model.*;
import json.model.cost_and_usages.CostAndUsagesJson;
import security.CredentialsClient;

import util.DateUtil;

import java.time.LocalDate;


@AwsRequest
public class CostExplorerRequest extends RequestBase<CostExplorerResponseHandler> {


    public CostExplorerRequest() {

        handler = new CostExplorerResponseHandler();

    }

    public void CostAndUsages(Dimension dimension, String value, Metric metric, LocalDate startDate, LocalDate endDate) {
        com.amazonaws.services.costexplorer.model.Expression expression = new com.amazonaws.services.costexplorer.model.Expression();
        DimensionValues dimensions = new DimensionValues();
        dimensions.withKey(dimension);
        dimensions.withValues(value);

        expression.withDimensions(dimensions);

        final GetCostAndUsageRequest awsCERequest = new GetCostAndUsageRequest()
                .withTimePeriod(new DateInterval()
                        .withStart(DateUtil.ConvertDate(startDate)).withEnd(DateUtil.ConvertDate(endDate)))
                .withGranularity(Granularity.MONTHLY)
                .withMetrics(String.valueOf(metric))
                .withMetrics(Metric.USAGE_QUANTITY.name())
                .withFilter(expression);


        try {
            AWSCostExplorer ce = AWSCostExplorerClientBuilder.standard()
                    .withCredentials(new CredentialsClient().getCredentials())
                    .build();

            GetCostAndUsageResult res = ce.getCostAndUsage(awsCERequest);
            System.out.println(res.getResultsByTime());

            ce.shutdown();

        } catch (final Exception e) {
            System.out.println(e);
        }
    }

    public boolean CostAndUsagesWithGroupBy(LocalDate startDate, LocalDate endDate) {
        final GetCostAndUsageRequest awsCERequest = new GetCostAndUsageRequest()
                .withTimePeriod(new DateInterval().withStart(DateUtil.ConvertDate(startDate)).withEnd(DateUtil.ConvertDate(endDate)))
                .withGranularity(Granularity.MONTHLY)
                .withMetrics(Metric.UNBLENDED_COST.name())
                .withGroupBy(new GroupDefinition().withType("DIMENSION").withKey(Dimension.SERVICE.toString()));


        try {
            AWSCostExplorer ce = AWSCostExplorerClientBuilder.standard()
                    .withCredentials(new CredentialsClient().getCredentials())
                    .build();


            GetCostAndUsageResult ceResult = ce.getCostAndUsage(awsCERequest);

            handler.CostAndUsagesWithGroupByResult(ceResult);

            ce.shutdown();

            return true;


        } catch (final Exception e) {
            System.out.println(e);
        }

        return false;
    }


    public void CostForeCast(Dimension dimension, String value, LocalDate startDate, LocalDate endDate) {
        com.amazonaws.services.costexplorer.model.Expression expression = new com.amazonaws.services.costexplorer.model.Expression();

        DimensionValues dimensions = new DimensionValues();
        dimensions.withKey(dimension);
        dimensions.withValues(value);

        expression.withDimensions(dimensions);

        final GetCostForecastRequest awsCFRequest = new GetCostForecastRequest()
                .withTimePeriod(new DateInterval()
                        .withStart(DateUtil.ConvertDate(startDate)).withEnd(DateUtil.ConvertDate(endDate)))
                .withGranularity(Granularity.MONTHLY)
                .withMetric(Metric.BLENDED_COST)
                .withFilter(expression);


        try {
            AWSCostExplorer ce = AWSCostExplorerClientBuilder.standard()
                    .withCredentials(new CredentialsClient().getCredentials())
                    .build();


            System.out.println(ce.getCostForecast(awsCFRequest));


        } catch (final Exception e) {
            System.out.println(e);
        }
    }

    public void CostExplorerJsonData() {

        Runner runner = new Runner();

        CostAndUsagesJson result = runner.MakeRequest();

        handler.CostAndUsagesJsonResult(result);

    }

}
