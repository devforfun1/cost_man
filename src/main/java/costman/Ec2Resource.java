package costman;

import Enum.budget.BudgetStatus;
import aws.cli.AwsCLIRequest;
import exception.BudgetNotSupportedException;
import handler.response.model.BudgetResponseModel;
import handler.response.model.Ec2CeDataModel;
import software.amazon.awssdk.services.dynamodb.model.Get;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

// Refactor class later ...
public class Ec2Resource {

    private AwsCLIRequest awsCLIRequest;

    public Ec2Resource() {

        awsCLIRequest = new AwsCLIRequest();
    }

    public List<String> CalculateEc2InstancesToShutdown(Dictionary<String, Ec2CeDataModel> ec2SumValesDict, BudgetStatus status,
                                                        BudgetResponseModel responseModel) throws BudgetNotSupportedException {

        List<String> instanceIdsToShutdown = null;

        if (status == BudgetStatus.CLOSE_TO_LIMIT || status == BudgetStatus.LEVEL2) {

            instanceIdsToShutdown = new ArrayList<>();

            Enumeration<String> e = ec2SumValesDict.keys();

            System.out.println("budgetResponseModel -> " + responseModel.toString() );

            String id;
            double ec2InstancePortionOfBudgetPercentage = 0;


            double predictedEc2Cost = 0;


            Dictionary<String,Double> predictedCostDict = new Hashtable<>();


            if (status == BudgetStatus.CLOSE_TO_LIMIT) {

                while (e.hasMoreElements()) {

                    id = e.nextElement();

                    System.out.println();
                    System.out.println("id -> " + id + "\ncost -> " + ec2SumValesDict.get(id).getSummedPriced() +
                            "\nSummed Usage -> " + ec2SumValesDict.get(id).getSummedUsageQuantity());


                    System.out.println("ec2 instance percentage -> " + GetPercentageOfBudget(responseModel,
                            ec2SumValesDict.get(id).getSummedPriced()));

                    ec2InstancePortionOfBudgetPercentage = ec2InstancePortionOfBudgetPercentage + GetPercentageOfBudget(responseModel,
                            ec2SumValesDict.get(id).getSummedPriced());

                    predictedCostDict.put(id,GetPredictedCostForBudgetEndDate(ec2SumValesDict.get(id).getSummedPriced(),
                            responseModel.getEndDate()));


                    predictedEc2Cost = predictedEc2Cost + predictedCostDict.get(id);



                }
                System.out.println("percentage remaining of budget -> "+responseModel.GetRemainingPercentage());

                Enumeration<String> e2 = ec2SumValesDict.keys();


                    double predictedEc2CostPlusAmountUsed = predictedEc2Cost + responseModel.getAmountUsed().doubleValue();

                System.out.println("predictedEc2CostPlusAmountUsed -> " +predictedEc2CostPlusAmountUsed);
                if (predictedEc2CostPlusAmountUsed >= responseModel.getLimit().doubleValue()) {

                    System.out.println("in predicted block");

               double predictedCostOverDue  =  predictedEc2CostPlusAmountUsed - responseModel.getLimit().doubleValue();


                    System.out.println("Predicted cost overdue -> " + predictedCostOverDue);

                    /**
                     * List that contains elements in e2 enumeration that will solve the budget issue on its own
                     */
                  List<String> singleElementSolution = new ArrayList<>();
                    /**
                     *  Elements in e2 enumeration that dosnt solve the budget issue on its own
                     */
                  List<String> elementCombinedSolution = new ArrayList<>();

                    while (e2.hasMoreElements()) {

                        String idE2 = e2.nextElement();



                        if(predictedCostDict.get(idE2) - predictedCostOverDue <= responseModel.getLimit().doubleValue())
                        {

                            singleElementSolution.add(idE2);
                        }

                        else {

                            elementCombinedSolution.add(idE2);
                        }


                        /**
                         * If all elements solve the budget issue compare usages
                         */
                        if(!singleElementSolution.isEmpty() && elementCombinedSolution.isEmpty()){

                            // Compare usage with JSON data from Resource Storage
                            // and return the instance id that has the least usages
                        }

                        else{

                                //solve it with combined solution

                            //TODO: finish implementation
                        }




                    }

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


        System.out.println((ec2InstanceSum) * daysLeftOfBudget);

        return (ec2InstanceSum/14) * daysLeftOfBudget;

    }



}
