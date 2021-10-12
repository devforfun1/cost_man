package costman;

import Enum.budget.BudgetStatus;

import exception.BudgetNotSupportedException;
import handler.response.model.BudgetResponseModel;
import handler.response.model.Ec2CeDataModel;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Ec2Resource {


    public Ec2Resource() {


    }

    public List<String> CalculateEc2InstancesToShutdown(Dictionary<String, Ec2CeDataModel> ec2SumValuesDict, BudgetStatus status,
                                                        BudgetResponseModel responseModel) throws BudgetNotSupportedException {
        List<String> instanceIdsToShutdown;

        if (status == BudgetStatus.CLOSE_TO_LIMIT || status == BudgetStatus.LEVEL2) {

            instanceIdsToShutdown = new ArrayList<>();

            Enumeration<String> e = ec2SumValuesDict.keys();

            //System.out.println("budgetResponseModel -> " + responseModel.toString());

            String id;
            double ec2InstancePortionOfBudgetPercentage = 0;

            double predictedEc2Cost = 0;

            Dictionary<String, Double> predictedCostDict = new Hashtable<>();

            if (status == BudgetStatus.CLOSE_TO_LIMIT) {

                while (e.hasMoreElements()) {

                    id = e.nextElement();

                    ec2InstancePortionOfBudgetPercentage = ec2InstancePortionOfBudgetPercentage + GetPercentageOfBudget(responseModel,
                            ec2SumValuesDict.get(id).getSummedPriced());

                    predictedCostDict.put(id, GetPredictedCostForBudgetEndDate(ec2SumValuesDict.get(id).getSummedPriced(),
                            responseModel.getEndDate()));

                    predictedEc2Cost = predictedEc2Cost + predictedCostDict.get(id);
                }

                //System.out.println("percentage remaining of budget -> " + responseModel.GetRemainingPercentage());

                Enumeration<String> e2 = ec2SumValuesDict.keys();

                double predictedEc2CostPlusAmountUsed = predictedEc2Cost + responseModel.getAmountUsed().doubleValue();

                if (predictedEc2CostPlusAmountUsed >= responseModel.getLimit().doubleValue()) {

                    double predictedCostOverDue = predictedEc2CostPlusAmountUsed - responseModel.getLimit().doubleValue();

                    System.out.println("Predicted cost overdue -> " + predictedCostOverDue);

                    /**
                     * List that contains elements in e2 enumeration that will solve the budget issue on its own
                     */
                    List<String> singleElementSolution = new ArrayList<>();
                    /**
                     *  Elements in e2 enumeration that doesn't solve the budget issue on its own
                     */
                    List<String> elementCombinedSolution = new ArrayList<>();

                    while (e2.hasMoreElements()) {

                        String idE2 = e2.nextElement();


                        if (predictedCostDict.get(idE2) >= predictedCostOverDue)
                            singleElementSolution.add(idE2);
                        else
                            elementCombinedSolution.add(idE2);
                    }

                    /**
                     * If all elements solve the budget issue by themselves,  compare their usages and-
                     * -return the least used ec2 instance id
                     */
                    if (!singleElementSolution.isEmpty() && elementCombinedSolution.isEmpty()) {

                        System.out.println("in single");
                        instanceIdsToShutdown.add(SingleElementSolution(ec2SumValuesDict));

                    } else {

                        /**
                         * Create unions of possible solutions and compare their usage
                         */

                        System.out.println("in else");
                        instanceIdsToShutdown = CombinedElementSolution(ec2SumValuesDict, predictedCostDict,
                                predictedCostOverDue);


                    }
                    return instanceIdsToShutdown;
                }

            }

        } else {
            throw new BudgetNotSupportedException(status.name());
        }

        return instanceIdsToShutdown;
    }


    private Double GetPercentageOfBudget(BudgetResponseModel responseModel, double sum) {

        return sum / responseModel.getLimit().doubleValue();

    }


    /**
     * Returns the predicted cost of an EC2 instance sum
     * The EC2 instances shall hold data from an interval of 14 days
     *
     * @param ec2InstanceSum
     * @param endDate
     * @return
     */
    private double GetPredictedCostForBudgetEndDate(double ec2InstanceSum, LocalDate endDate) {

        long daysLeftOfBudget = ChronoUnit.DAYS.between(LocalDate.now(), endDate);

        return (ec2InstanceSum / 14) * daysLeftOfBudget;

    }


    /**
     * Compare usages sum and return the instance id of the least used ec2
     *
     * @param ec2SumValuesDict
     * @return
     */
    private String SingleElementSolution(Dictionary<String, Ec2CeDataModel> ec2SumValuesDict) {

        String selectedId = "";
        double usageQuantitySum = 0;

        Enumeration<String> e = ec2SumValuesDict.keys();

        while (e.hasMoreElements()) {

            String id = e.nextElement();

            if (ec2SumValuesDict.get(id).getSummedUsageQuantity() > usageQuantitySum) {

                selectedId = id;
                // System.out.println("selected id: singleElementSolution -> " + selectedId);
            }

            usageQuantitySum = ec2SumValuesDict.get(id).getSummedUsageQuantity();
            //System.out.println("Usage quantitySum : singleElementSolution -> " + usageQuantitySum);
        }

        return selectedId;

    }

    private List<String> CombinedElementSolution(Dictionary<String, Ec2CeDataModel> ec2SumValuesDict, Dictionary<String, Double> predictedCostDict,
                                                 double predictedCostOverDue) {


        List<List<String>> unionsCombinedSolutionsList = new ArrayList<>();

        List<String> combinedSolution = null;

        List<String> ec2InstanceIds = Collections.list(ec2SumValuesDict.keys());

        String enumerationId;

        for (String ec2Id : ec2InstanceIds) {


            Enumeration<String> e = ec2SumValuesDict.keys();

            enumerationId = e.nextElement();

            while (e.hasMoreElements()) {

                System.out.println((predictedCostDict.get(ec2Id) + predictedCostDict.get(enumerationId)) >= predictedCostOverDue);

                if ((predictedCostDict.get(ec2Id) + predictedCostDict.get(enumerationId)) >= predictedCostOverDue) {

                    combinedSolution = new ArrayList<>();

                    if (!ec2Id.matches(enumerationId)) {
                        combinedSolution.add(ec2Id);
                        combinedSolution.add(enumerationId);

                    }
                }

                enumerationId = e.nextElement();
            }
            if (!combinedSolution.isEmpty())
                unionsCombinedSolutionsList.add(combinedSolution);
        }

        //TODO: finish implementation

        return GetLeastUsedCombination(unionsCombinedSolutionsList, ec2SumValuesDict);

    }


    private List<String> GetLeastUsedCombination(List<List<String>> unionsCombinedSolutionsList,
                                                 Dictionary<String, Ec2CeDataModel> ec2SumValuesDict) {


        List<String> selectedList = null;

        double currUsageCombinationSum;
        double leastUsageCombinationSum = 0;

        boolean isFirst = true;

        for (List<String> combinationList : unionsCombinedSolutionsList) {
            currUsageCombinationSum = 0;

            for (String instanceId : combinationList) {
                System.out.println("instanceId -> " + instanceId);
                if (isFirst)
                    selectedList = combinationList;

                currUsageCombinationSum = currUsageCombinationSum +
                        ec2SumValuesDict.get(instanceId).getSummedUsageQuantity();

                if (isFirst)
                    leastUsageCombinationSum = currUsageCombinationSum;
            }
            System.out.println("currUsage -> " + currUsageCombinationSum);
            System.out.println("selectedUsage -> " + leastUsageCombinationSum);
            if (currUsageCombinationSum < leastUsageCombinationSum && !isFirst) {
                selectedList = combinationList;
                leastUsageCombinationSum = currUsageCombinationSum;
            }
            System.out.println("Curr usage sum ->" + currUsageCombinationSum);
            if (isFirst)
                isFirst = false;

        }

        return selectedList;
    }

}
