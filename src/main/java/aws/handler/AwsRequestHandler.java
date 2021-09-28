package aws.handler;

import Enum.AwsRequest;
import aws.json_api_gateway_caller.Runner;
import aws.request.Budget;
import aws.request.CostExplorer;
import com.amazonaws.services.costexplorer.model.Dimension;
import com.amazonaws.services.costexplorer.model.Metric;
import singleton.DataStorage;
import util.DateUtil;

import java.time.LocalDate;


public class AwsRequestHandler {

    private CostExplorer costExplorer;

    public AwsRequestHandler() {

        costExplorer = new CostExplorer();
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


        costExplorer.CostAndUsages(Dimension.LINKED_ACCOUNT, DataStorage.getInstance().getAwsAccountNr(), Metric.UNBLENDED_COST,
                DateUtil.GetFirstDateOfCurrentMonth(), LocalDate.now());

    }

    private void TotalCostWithGroupBy() {


        costExplorer.CostAndUsagesWithGroupBy(DateUtil.GetFirstDateOfCurrentMonth(), LocalDate.now());

    }

    private void MonthlyBudget() {

        Budget budget = new Budget();
        budget.BudgetWithFilter(DataStorage.getInstance().getAwsAccountNr(), "Monthly budget");

    }

    private void CostForeCast() {


        costExplorer.CostForeCast(Dimension.LINKED_ACCOUNT, "AWS",
                LocalDate.now(), LocalDate.now().plusMonths(3));

    }

    private void CostExplorerJsonData() {

        Runner runner = new Runner();

        runner.MakeRequest();

    }


}