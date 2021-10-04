package handler;

import Enum.request.AwsRequest;
import aws.api.request.budget.BudgetRequest;
import aws.api.request.cost_explorer.CostExplorerRequest;
import aws.cli.AwsCLIRequest;
import com.amazonaws.services.costexplorer.model.Dimension;
import com.amazonaws.services.costexplorer.model.Metric;
import singleton.DataStorage;
import util.DateUtil;

import java.time.LocalDate;


public class AwsRequestHandler {


    private final CostExplorerRequest costExplorerRequest;
    private final AwsCLIRequest awsCLIRequest;

    public AwsRequestHandler() {


        costExplorerRequest = new CostExplorerRequest();
        awsCLIRequest = new AwsCLIRequest();
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
            case COST_AND_USAGES:
                CostAndUsages();
                break;
            case COST_AND_USAGES_WITH_RESOURCES:
                CostAndUsagesWithResources();
                break;
            case EC2_DATA:
                GetEC2Data();

        }
    }


    private void TotalCost() {


        costExplorerRequest.CostAndUsages(Dimension.LINKED_ACCOUNT, DataStorage.getInstance().getAwsAccountNr(), Metric.UNBLENDED_COST,
                DateUtil.GetFirstDateOfCurrentMonth(), LocalDate.now());

    }

    private void TotalCostWithGroupBy() {

        costExplorerRequest.CostAndUsagesWithGroupBy(DateUtil.GetFirstDateOfCurrentMonth(), LocalDate.now());


    }

    private void MonthlyBudget() {

        BudgetRequest budgetRequest = new BudgetRequest();
        budgetRequest.BudgetWithFilter(DataStorage.getInstance().getAwsAccountNr(), DataStorage.getInstance().getBudgetName());

    }

    private void CostForeCast() {


        costExplorerRequest.CostForeCast(Dimension.LINKED_ACCOUNT, "AWS",
                LocalDate.now(), LocalDate.now().plusMonths(3));

    }

    private void CostAndUsages() {

        costExplorerRequest.CostAndUsages();

    }

    private void CostAndUsagesWithResources() {

        costExplorerRequest.CostAndUsagesWithResources();

    }


    private void GetEC2Data() {

        awsCLIRequest.GetEC2Data();

    }


}
