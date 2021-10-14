package costman;

import Enum.budget.BudgetStatus;

import exception.BudgetNotSupportedException;
import handler.response.model.BudgetResponseModel;
import handler.response.model.Ec2CeDataModel;
import tool.ListTool;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Ec2ResourceGroup {


    public Ec2ResourceGroup() {


    }

    /**
     * Calculate ec2 instances to shut down based on budget,usages and price
     * @param ec2SumValuesDict
     * @param status
     * @param responseModel
     * @return
     * @throws BudgetNotSupportedException
     */

    public List<String> CalculateEc2InstancesToShutdown(Dictionary<String, Ec2CeDataModel> ec2SumValuesDict, BudgetStatus status,
                                                        BudgetResponseModel responseModel) throws BudgetNotSupportedException {
        List<String> instanceIdsToShutdown;

        if (status == BudgetStatus.CLOSE_TO_LIMIT || status == BudgetStatus.LEVEL2) {

            instanceIdsToShutdown = new ArrayList<>();

            Enumeration<String> e = ec2SumValuesDict.keys();

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

                System.out.println("percentage remaining of budget -> " + responseModel.GetRemainingPercentage());



                double predictedEc2CostPlusAmountUsed = predictedEc2Cost + responseModel.getAmountUsed().doubleValue();

                /**
                 * No need to modify since predicted budget is met
                 */
                if (predictedEc2CostPlusAmountUsed >= responseModel.getLimit().doubleValue()) {

                    double predictedCostOverDue = predictedEc2CostPlusAmountUsed - responseModel.getLimit().doubleValue();

                    System.out.println("Predicted cost overdue -> " + predictedCostOverDue);

                    /**
                     * Create unions of possible solutions and compare their usage
                     */

                    instanceIdsToShutdown = CombinedElementSolution(ec2SumValuesDict, predictedCostDict,
                            predictedCostOverDue);


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
     * @param ec2SumValuesDict
     * @param predictedCostDict
     * @param predictedCostOverDue
     * @return
     */
    private List<String> CombinedElementSolution(Dictionary<String, Ec2CeDataModel> ec2SumValuesDict, Dictionary<String, Double> predictedCostDict,
                                                 double predictedCostOverDue) {

        /**
         *  So the execution time will be reasonable
         */
        if (ec2SumValuesDict.size() < 7) {

            return CombinedElementSolutionRecursive(ec2SumValuesDict, predictedCostDict, predictedCostOverDue);
        } else {

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

            return GetLeastUsedCombination(unionsCombinedSolutionsList, ec2SumValuesDict);
        }

    }

    /**
     * 1.Makes a call to a recursive method that finds the unique combinations and returns them into a List
     * 2. Check possible combinations that won't break budget and adds them to the OK list
     * 3.Invokes a method to GetLeastUsedCombination
     * @param ec2SumValuesDict
     * @param predictedCostDict
     * @param predictedCostOverDue
     * @return
     */
    private List<String> CombinedElementSolutionRecursive(Dictionary<String, Ec2CeDataModel> ec2SumValuesDict, Dictionary<String, Double> predictedCostDict,
                                                          double predictedCostOverDue) {

        ListTool<String> listTool = new ListTool<>();

        List<String> ec2InstanceIds = Collections.list(ec2SumValuesDict.keys());

        List<List<String>> resultList = new ArrayList<>();

        List<List<String>> unionsCombinedSolutionsList = listTool.FindAllCombinationsWithUniqueElements
                (ec2InstanceIds, ec2InstanceIds.size(), resultList);


        List<List<String>> combinedSolutionCostOk = new ArrayList<>();

        double combinationCostSum;

        for (List<String> combinationList : unionsCombinedSolutionsList) {

            combinationCostSum = 0;

            for (String ec2InstanceId : combinationList) {

                combinationCostSum = combinationCostSum + predictedCostDict.get(ec2InstanceId);
            }

            if (combinationCostSum >= predictedCostOverDue) {

                combinedSolutionCostOk.add(combinationList);
            }
        }

        return GetLeastUsedCombination(combinedSolutionCostOk, ec2SumValuesDict);
    }



    /**
     * Get the least used combination (including combinations of single elements)
     *
     * @param unionsCombinedSolutionsList
     * @param ec2SumValuesDict
     * @return
     */
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
