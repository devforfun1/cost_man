package handler.response.budget;

import handler.response.base.ResponseHandlerBase;
import model.api.response.BudgetResponseModel;
import Enum.budget.BudgetStatus;

public class BudgetResponseHandler extends ResponseHandlerBase {

    public BudgetResponseHandler() {
    }

    public void HandleBudgetResponse(BudgetResponseModel response) {

        System.out.println(response.toString());

        BudgetStatus status = GetBudgetStatus(response);

        System.out.println(status);

        switch (status) {
            case OK:
                BudgetOK(response);
                break;
            case CLOSE_TO_LIMIT:
                BudgetCloseToLimit(response);
                break;
            case URGENT:
                BudgetUrgent(response);
                break;
            case OVER_DUE:
                BudgetOverDue(response);
        }

    }

    private void BudgetOK(BudgetResponseModel response) {

        //TODO: Not yet implemented
    }


    private void BudgetCloseToLimit(BudgetResponseModel response) {

        //TODO: Not yet implemented

    }

    private void BudgetUrgent(BudgetResponseModel response) {

        // 1. Check resource prio list

        //2. IF EC2 in prip

        //TODO: Not yet implemente




    }

    private void BudgetOverDue(BudgetResponseModel response) {

        //TODO: Not yet implemented
    }

    private BudgetStatus GetBudgetStatus(BudgetResponseModel response) {

        BudgetStatus budgetStatus = BudgetStatus.NOT_DEFINED;

        System.out.println(response.GetPercentageLeft());

        double percentage = response.GetPercentageLeft();

        if (response.IsBudgetOverDue()) {
            budgetStatus = BudgetStatus.OVER_DUE;
        } else {
            if (percentage >= 0 && percentage <= 0.70) {

                budgetStatus = BudgetStatus.OK;
            } else if (percentage > 0.70 && percentage <= 0.89) {

                budgetStatus = BudgetStatus.CLOSE_TO_LIMIT;
            } else if (percentage >= 0.90 && percentage <= 1) {

                budgetStatus = BudgetStatus.URGENT;
            }

        }

        return budgetStatus;
    }
}
