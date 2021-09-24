package aws.handler;

import Enum.AwsRequest;
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

    public void MakeRequest(AwsRequest awsRequest) {


        switch (awsRequest) {
            case TOTAL_COST_LINKED_ACCOUNT:
                TotalCostLinkedAccount();
                break;
            case MONTHLY_BUDGET:
                MonthlyBudget();
                break;
            case COST_FORECAST:
                CostForeCast();

        }
    }


    private void TotalCostLinkedAccount() {


        costExplorer.CostAndUsages(Dimension.LINKED_ACCOUNT, DataStorage.getInstance().getAwsAccountNr(), Metric.UNBLENDED_COST,
                LocalDate.of(2021, 9, 1), LocalDate.now());

    }

    private void MonthlyBudget() {

        Budget budget = new Budget();
        budget.BudgetWithFilter(DataStorage.getInstance().getAwsAccountNr(), "Monthly budget");

    }

    private void CostForeCast() {


        costExplorer.CostForeCast(Dimension.LINKED_ACCOUNT, "AWS",
                LocalDate.now(), LocalDate.now().plusMonths(3));

    }


}
