package aws.request;

import annonation.AwsRequest;
import base.AwsBase;
import com.amazonaws.services.budgets.*;
import com.amazonaws.services.budgets.AWSBudgetsClientBuilder;

import com.amazonaws.services.budgets.model.DescribeBudgetRequest;
import com.amazonaws.services.budgets.model.DescribeBudgetsRequest;
import security.CredentialsClient;


@AwsRequest
public class Budget extends AwsBase {

    public Budget() {
    }

    public void BudgetWithFilter(String accountId, String budgetName) {

        final DescribeBudgetRequest budgetsRequest = new DescribeBudgetRequest();

        budgetsRequest
                .withAccountId(accountId)
                .withBudgetName(budgetName);

        try {
            AWSBudgets awsBudgets = AWSBudgetsClientBuilder.standard().
                    withCredentials(new CredentialsClient().getCredentials())
                    .build();


            System.out.println("Budget Limit -> "+awsBudgets.describeBudget(budgetsRequest).getBudget().getBudgetLimit());
            System.out.println("Budget Used -> "+awsBudgets.describeBudget(budgetsRequest).getBudget().getCalculatedSpend().getActualSpend());

        } catch (final Exception exception) {

            exception.printStackTrace();
        }

    }


}
