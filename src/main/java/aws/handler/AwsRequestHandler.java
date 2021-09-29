package aws.handler;

import Enum.AwsRequest;
import aws.json_api_gateway_caller.Runner;
import aws.request.BudgetRequest;
import aws.request.CostExplorerRequest;
import com.amazonaws.services.costexplorer.model.Dimension;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import com.amazonaws.services.costexplorer.model.Metric;
import json.model.cost_and_usages.CostAndUsagesJson;
import singleton.DataStorage;
import util.DateUtil;

import java.time.LocalDate;


public class AwsRequestHandler {

    private AwsResultHandler resultHandler;


    private CostExplorerRequest costExplorerRequest;

    public AwsRequestHandler() {

        resultHandler = new AwsResultHandler();
        costExplorerRequest = new CostExplorerRequest();
    }

    public void HandleRequest(AwsRequest awsRequest) {


        switch (awsRequest) {
            case TOTAL_COST:
                TotalCost();
                break;
            case TOTAL_COST_GROUP_BY:
                TotalCostWithGroupBy();
                break;
            case MONTHLY_BUDGET:
                MonthlyBudget();
                break;
            case COST_FORECAST:
                CostForeCast();
                break;
            case COST_EXPLORER_JSON_DATA:
                CostExplorerJsonData();

        }
    }


    private void TotalCost() {


        costExplorerRequest.CostAndUsages(Dimension.LINKED_ACCOUNT, DataStorage.getInstance().getAwsAccountNr(), Metric.UNBLENDED_COST,
                DateUtil.GetFirstDateOfCurrentMonth(), LocalDate.now());

    }

    private void TotalCostWithGroupBy() {

        GetCostAndUsageResult ceResult = costExplorerRequest.CostAndUsagesWithGroupBy(DateUtil.GetFirstDateOfCurrentMonth(), LocalDate.now());

        resultHandler.CostAndUsagesWithGroupByResult(ceResult);
    }

    private void MonthlyBudget() {

        BudgetRequest budget = new BudgetRequest();
        budget.BudgetWithFilter(DataStorage.getInstance().getAwsAccountNr(), DataStorage.getInstance().getBudgetName());

    }

    private void CostForeCast() {


        costExplorerRequest.CostForeCast(Dimension.LINKED_ACCOUNT, "AWS",
                LocalDate.now(), LocalDate.now().plusMonths(3));

    }

    private void CostExplorerJsonData() {

        Runner runner = new Runner();

        CostAndUsagesJson result = runner.MakeRequest();

        resultHandler.CostAndUsagesJsonResult(result);

    }


}
