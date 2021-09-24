package aws.request;

import annonation.AwsRequest;
import base.AwsBase;
import com.amazonaws.services.costexplorer.AWSCostExplorer;
import com.amazonaws.services.costexplorer.AWSCostExplorerClientBuilder;
import com.amazonaws.services.costexplorer.model.*;
import security.CredentialsClient;
import util.DateUtil;

import java.time.LocalDate;

@AwsRequest
public class CostExplorer extends AwsBase {

    public CostExplorer() {
    }

    public void CEWithDimension(Dimension dimension, String value, Metric metric, LocalDate startDate, LocalDate endDate) {
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
                .withFilter(expression);


        try {
            AWSCostExplorer ce = AWSCostExplorerClientBuilder.standard()
                    .withCredentials(new CredentialsClient().getCredentials())
                    .build();


            System.out.println(ce.getCostAndUsage(awsCERequest));

        } catch (final Exception e) {
            System.out.println(e);
        }
    }


}
