package aws.handler.budget;

import model.BudgetResponse;

public class BudgetResponseHandler {

    public BudgetResponseHandler() {
    }

    public void HandleBudgetResponse(BudgetResponse response) {

        System.out.println(response.toString());
        System.out.println(response.IsBudgetOverDue());

        if (response.IsBudgetOverDue()) {
            HandleBudgetOverDue(response);
        } else {
            HandleBudgetOverDue(response);
        }

    }

    private void HandleBudgetOverDue(BudgetResponse response) {


    }

    private void HandleBudgetUnderDue(BudgetResponse response) {


    }

}
