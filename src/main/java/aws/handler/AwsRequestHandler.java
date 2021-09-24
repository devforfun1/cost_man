package aws.handler;

import Enum.AwsRequest;
import aws.request.Budget;
import aws.request.CostExplorer;
import com.amazonaws.services.costexplorer.model.Dimension;
import com.amazonaws.services.costexplorer.model.Metric;
import singleton.DataStorage;

import java.time.LocalDate;

public class AwsRequestHandler {

    public AwsRequestHandler() {
    }

    public void MakeRequest(AwsRequest awsRequest) {


        switch (awsRequest) {
            case TOTAL_COST_LINKED_ACCOUNT:
                TotalCostLinkedAccount();
                break;
            case MONTHLY_BUDGET:
                MonthlyBudget();
                break;

        }
    }


    private void TotalCostLinkedAccount() {

        CostExplorer costExplorer = new CostExplorer();

        costExplorer.CEWithDimension(Dimension.LINKED_ACCOUNT, DataStorage.getInstance().getAwsAccountNr(), Metric.UNBLENDED_COST,
                LocalDate.of(2021,9,1), LocalDate.now());

    }

    private void MonthlyBudget() {

        Budget budget = new Budget();
        budget.BudgetWithFilter(DataStorage.getInstance().getAwsAccountNr(),"Monthly budget");

    }


}
