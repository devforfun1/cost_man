package handler.response.budget;

import Enum.priority.PriorityQueueType;
import Enum.priority.ResourceGroupPriorities;
import aws.api.request.cost_explorer.CostExplorerRequest;
import aws.cli.AwsCLIRequest;
import costman.Ec2Resource;
import datastorage.ResourceStorage;
import datastorage.db.PriorityService;
import exception.BudgetNotSupportedException;
import factory.priority.Producer;
import factory.priority.ResourceGroupFactory;
import handler.response.base.ResponseHandlerBase;
import handler.response.model.BudgetResponseModel;
import Enum.budget.BudgetStatus;
import handler.response.model.Ec2CeDataModel;
import priority.ResourceGroupPriority;
import software.amazon.awssdk.services.budgets.model.BudgetsResponse;


import java.util.Dictionary;
import java.util.List;


public class BudgetResponseHandler extends ResponseHandlerBase {

    private PriorityService priorityService;
    private AwsCLIRequest awsCLIRequest;


    public BudgetResponseHandler() {

        priorityService = new PriorityService();
        awsCLIRequest = new AwsCLIRequest();
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

        PriorityQueueType queueType = GetSelectedPriority();

        if (queueType == PriorityQueueType.RESOURCE_GROUPS) {

            ResourceGroupPriorities queueElement = GetResourceGroupPriority().getPriorityQueue().poll();

            if (queueElement == ResourceGroupPriorities.EC2) {

                Ec2ResourceGroupOperation(BudgetStatus.OK, response);

            }

        }
    }


    private void BudgetCloseToLimit(BudgetResponseModel response) {

        PriorityQueueType queueType = GetSelectedPriority();

        if (queueType == PriorityQueueType.RESOURCE_GROUPS) {

            ResourceGroupPriorities queueElement = GetResourceGroupPriority().getPriorityQueue().poll();

            if (queueElement == ResourceGroupPriorities.EC2) {

                Ec2ResourceGroupOperation(BudgetStatus.CLOSE_TO_LIMIT, response);

            }
        }
    }

    private void BudgetUrgent(BudgetResponseModel response) {

        PriorityQueueType queueType = GetSelectedPriority();

        if (queueType == PriorityQueueType.RESOURCE_GROUPS) {


            ResourceGroupPriorities queueElement = GetResourceGroupPriority().getPriorityQueue().poll();
            if (queueElement == ResourceGroupPriorities.EC2) {


                Ec2ResourceGroupOperation(BudgetStatus.URGENT, response);

            } else if (queueElement == ResourceGroupPriorities.VPC) {

                //TODO: Not yet implemented
            } else if (queueElement == ResourceGroupPriorities.EKS) {

                //TODO: Not yet implemented
            }
        }

    }

    private void BudgetOverDue(BudgetResponseModel response) {

        Ec2ResourceGroupOperation(BudgetStatus.OVER_DUE,response);
    }

    /**
     * Threshold values for different budget statuses
     *
     * @param response
     * @return
     */

    private BudgetStatus GetBudgetStatus(BudgetResponseModel response) {

        BudgetStatus budgetStatus = BudgetStatus.NOT_DEFINED;

        System.out.println(response.GetPercentageLeft());

        double percentage = response.GetPercentageLeft();

        if (response.IsBudgetOverDue()) {
            budgetStatus = BudgetStatus.OVER_DUE;
        } else {

            if (percentage >= 0 && percentage <= 0.45) {
                budgetStatus = BudgetStatus.OK;
            } else if (percentage > 0.45 && percentage <= 0.70) {
                budgetStatus = BudgetStatus.LEVEL2;
            } else if (percentage > 0.70 && percentage <= 0.89) {

                budgetStatus = BudgetStatus.CLOSE_TO_LIMIT;
            } else if (percentage >= 0.90 && percentage <= 1) {

                budgetStatus = BudgetStatus.URGENT;
            }

        }
        return budgetStatus;
    }

    /**
     * Gets the selected priority policy from the repository
     *
     * @return
     */
    private PriorityQueueType GetSelectedPriority() {

        return priorityService.GetSelectedPriorityQueue().getType();
    }

    /**
     * Gets the wrapper for the ResourceGroup Priority Queue
     *
     * @return
     */
    private ResourceGroupPriority GetResourceGroupPriority() {

        ResourceGroupFactory factory = (ResourceGroupFactory) Producer.GetFactory();

        ResourceGroupPriority rg = factory.Create();

        return rg;
    }

    /**
     * Start or stops EC2 instances based on the budget status and their previous state
     * This method should only be used when resource group priority is used
     *
     * @param status
     */
    private void Ec2ResourceGroupOperation(BudgetStatus status, BudgetResponseModel response) {

        ResourceStorage.getInstance().Ec2OperationRunning(true);

        // Sets EC2 information data in ResourceStorage (Singleton)
        awsCLIRequest.GetEC2Data();

        while (ResourceStorage.getInstance().IsEc2OperationRunning()) {

            // wait for thread in awsCLIRequest.GetEC2Data() to finish
        }

        List<String> ec2InstanceIds;

        // Starts all stopped Ec2 instances
        if (status == BudgetStatus.OK) {
            ec2InstanceIds = ResourceStorage.getInstance().getEc2StoppedInstances();
            awsCLIRequest.StartEC2Instances(ec2InstanceIds);
        } else if (status == BudgetStatus.CLOSE_TO_LIMIT) {

            Ec2Resource ec2Resource = new Ec2Resource();

            CostExplorerRequest ceRequest = new CostExplorerRequest();
            ec2InstanceIds = ResourceStorage.getInstance().getEc2RunningInstances();

            Dictionary<String, Ec2CeDataModel> ec2SumValesDict = ceRequest.GetEc2UsagesValueDict(ec2InstanceIds);


            try {
                ec2Resource.CalculateEc2InstancesToShutdown(ec2SumValesDict, status, response);
            } catch (BudgetNotSupportedException e) {
                e.printStackTrace();
            }
        }

        //Stops all running Ec2 instances
        else if (status == BudgetStatus.URGENT || status == BudgetStatus.OVER_DUE) {
            ec2InstanceIds = ResourceStorage.getInstance().getEc2RunningInstances();
            awsCLIRequest.StopEC2Instances(ec2InstanceIds);
        }

        ResourceStorage.getInstance().ClearLists();
    }

}
