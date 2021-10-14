package handler.response.budget;

import Enum.priority.PriorityQueueType;
import Enum.priority.ResourceType;
import aws.api.request.cost_explorer.CostExplorerRequest;
import aws.cli.AwsCLIRequest;
import costman.Ec2ResourceGroup;
import datastorage.ResourceStorage;
import datastorage.db.PriorityService;
import exception.BudgetNotSupportedException;
import factory.priority.Producer;
import factory.priority.ResourceGroupFactory;
import factory.priority.ResourcePriorityFactory;
import handler.response.base.ResponseHandlerBase;
import handler.response.model.BudgetResponseModel;
import Enum.budget.BudgetStatus;
import handler.response.model.Ec2CeDataModel;
import priority.ResourceGroupPriority;
import priority.ResourcePriority;
import priority.model.ResourcePriorityQueueElement;


import java.util.Dictionary;
import java.util.List;
import java.util.Queue;


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

            ResourceType queueElement = GetResourceGroupPriority().getPriorityQueue().poll();

            if (queueElement == ResourceType.EC2) {

                Ec2ResourceGroupOperation(BudgetStatus.OK, response);

            }

        }

        else if (queueType == PriorityQueueType.RESOURCE_IDS) {

            ResourcePriority resourcePriority = new ResourcePriority();

            Queue<ResourcePriorityQueueElement> queue =  resourcePriority.getPriorityQueue();

                //TODO: Finish implementation


        }

        else {
        }
    }


    private void BudgetCloseToLimit(BudgetResponseModel response) {

        PriorityQueueType queueType = GetSelectedPriority();

        if (queueType == PriorityQueueType.RESOURCE_GROUPS) {

            ResourceType queueElement = GetResourceGroupPriority().getPriorityQueue().poll();

            if (queueElement == ResourceType.EC2) {

                Ec2ResourceGroupOperation(BudgetStatus.CLOSE_TO_LIMIT, response);

            }
        } else if (queueType == PriorityQueueType.RESOURCE_IDS) {

            ResourcePriority resourcePriority = GetResourcePriority();

            //TODO: Finish implementation


        } else {
        }
    }

    private void BudgetUrgent(BudgetResponseModel response) {

        PriorityQueueType queueType = GetSelectedPriority();

        if (queueType == PriorityQueueType.RESOURCE_GROUPS) {


            ResourceType queueElement = GetResourceGroupPriority().getPriorityQueue().poll();
            if (queueElement == ResourceType.EC2) {


                Ec2ResourceGroupOperation(BudgetStatus.URGENT, response);

            } else if (queueElement == ResourceType.VPC) {

                //TODO: Not yet implemented
            } else if (queueElement == ResourceType.EKS) {

                //TODO: Not yet implemented
            }
        } else if (queueType == PriorityQueueType.RESOURCE_IDS) {
        } else {
        }

    }

    private void BudgetOverDue(BudgetResponseModel response) {

        PriorityQueueType queueType = GetSelectedPriority();

        Ec2ResourceGroupOperation(BudgetStatus.OVER_DUE, response);
        if (queueType == PriorityQueueType.RESOURCE_GROUPS) {

            //TODO: Finish implementation
        }
        else if (queueType == PriorityQueueType.RESOURCE_IDS) {

            //TODO: Finish implementation
        } else {
        }
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

       return factory.Create();


    }

    private ResourcePriority GetResourcePriority(){

        ResourcePriorityFactory factory = (ResourcePriorityFactory) Producer.GetFactory();

        return factory.Create();
    }

    private void Ec2ResourceOperation(BudgetStatus status, BudgetResponseModel response, Queue<String> instanceIdQueue){

        //TODO: Finish implementation

       if(status == BudgetStatus.OK){


       }

        if(status == BudgetStatus.CLOSE_TO_LIMIT){

        }




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

            Ec2ResourceGroup ec2ResourceGroup = new Ec2ResourceGroup();

            CostExplorerRequest ceRequest = new CostExplorerRequest();
            ec2InstanceIds = ResourceStorage.getInstance().getEc2RunningInstances();

            Dictionary<String, Ec2CeDataModel> ec2SumValesDict = ceRequest.GetEc2UsagesValueDict(ec2InstanceIds);


            try {

                ec2InstanceIds = ec2ResourceGroup.CalculateEc2InstancesToShutdown(ec2SumValesDict, status, response);
                awsCLIRequest.StopEC2Instances(ec2InstanceIds);

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
