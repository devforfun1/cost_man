package handler.response.budget;

import Enum.priority.PriorityQueueType;
import Enum.priority.ResourceGroupPriorities;
import aws.cli.AwsCLIRequest;
import datastorage.ResourceStorage;
import datastorage.db.PriorityService;
import factory.priority.Producer;
import factory.priority.ResourceGroupFactory;
import handler.response.base.ResponseHandlerBase;
import handler.response.model.BudgetResponseModel;
import Enum.budget.BudgetStatus;
import priority.ResourceGroupPriority;


import java.util.List;

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

        PriorityService service = new PriorityService();

        PriorityQueueType queueType = service.GetSelectedPriorityQueue().getType();

        if (queueType == PriorityQueueType.RESOURCE_GROUPS) {

            ResourceGroupPriorities queueElement = ResourceGroupPriority();

            if (queueElement == ResourceGroupPriorities.EC2) {

                AwsCLIRequest awsCLIRequest = new AwsCLIRequest();

                ResourceStorage.getInstance().Ec2OperationRunning(true);

                awsCLIRequest.GetEC2Data();


                while (ResourceStorage.getInstance().IsEc2OperationRunning()) {

                    // Wait
                }

                List<String> runningEc2s = ResourceStorage.getInstance().getEc2RunningInstances();

                awsCLIRequest.StopEC2Instances(runningEc2s);

            }
        }

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

    private ResourceGroupPriorities ResourceGroupPriority() {

        ResourceGroupFactory factory = (ResourceGroupFactory) Producer.GetFactory();

        ResourceGroupPriority rg = factory.Create();

        return rg.getPriorityQueue().poll();


    }

}
