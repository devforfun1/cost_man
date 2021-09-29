package aws.handler.budget;

import base.ResponseHandlerBase;
import model.BudgetResponse;
import Enum.BudgetStatus;

public class BudgetResponseHandler extends ResponseHandlerBase {

    public BudgetResponseHandler() {
    }

    public void HandleBudgetResponse(BudgetResponse response) {

        System.out.println(response.toString());

        BudgetStatus status = GetBudgetStatus(response.GetPercentageLeft());

        switch (status) {
            case OK:
                BudgetOK(response);
                break;
            case CLOSE_TO_LIMIT:
                BudgetCloseToLimit(response);
                break;
            case URGENT: BudgetUrgent(response);
                break;
            case OVER_DUE: BudgetOverDue(response);
        }

    }

    private void BudgetOK(BudgetResponse response) {
    }


    private void BudgetCloseToLimit(BudgetResponse response) {



    }

    private void BudgetUrgent(BudgetResponse response) {
    }

    private void BudgetOverDue(BudgetResponse response) {


    }

    private BudgetStatus GetBudgetStatus(int percentage) {

        if (percentage >= 0 && percentage <= 70) {

            return BudgetStatus.OK;
        } else if (percentage > 70 && percentage <= 89) {

            return BudgetStatus.CLOSE_TO_LIMIT;
        } else if (percentage >= 90 && percentage <= 100) {

            return BudgetStatus.URGENT;
        } else
            return BudgetStatus.OVER_DUE;


    }
}
