package aws.api.request.budget;

import handler.response.budget.BudgetResponseHandler;
import aws.api.request.base.RequestBase;
import com.amazonaws.services.budgets.*;
import com.amazonaws.services.budgets.AWSBudgetsClientBuilder;

import com.amazonaws.services.budgets.model.Budget;
import com.amazonaws.services.budgets.model.DescribeBudgetRequest;
import handler.response.model.BudgetResponseModel;
import security.CredentialsClient;

import java.time.LocalDateTime;


public class BudgetRequest extends RequestBase<BudgetResponseHandler> {



    public BudgetRequest() {

        handler = new BudgetResponseHandler();
    }


    public boolean BudgetWithFilter(String accountId, String budgetName) {

        final DescribeBudgetRequest budgetsRequest = new DescribeBudgetRequest();

        budgetsRequest
                .withAccountId(accountId)
                .withBudgetName(budgetName);

        try {
            AWSBudgets awsBudgets = AWSBudgetsClientBuilder.standard().
                    withCredentials(new CredentialsClient().getCredentials())
                    .build();

            Budget budget = awsBudgets.describeBudget(budgetsRequest).getBudget();


            BudgetResponseModel response = new BudgetResponseModel(LocalDateTime.now(),
                    budget.getCalculatedSpend().getActualSpend().getAmount(),
                    budget.getBudgetLimit().getAmount(),
                    budget.getBudgetLimit().getUnit());


            awsBudgets.shutdown();

            handler.HandleBudgetResponse(response);

            return true;

        } catch (final Exception exception) {

            exception.printStackTrace();
        }

        return false;
    }


}
