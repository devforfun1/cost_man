package costman;

import Enum.budget.BudgetStatus;
import aws.cli.AwsCLIRequest;
import exception.BudgetNotSupportedException;
import handler.response.model.BudgetResponseModel;
import handler.response.model.Ec2CeDataModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

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

            String id;
            double ec2InstancePortionOfBudgetPercentage = 0;
            double predictedPrice = 0;

            if (status == BudgetStatus.CLOSE_TO_LIMIT) {

                while (e.hasMoreElements()) {

                    id = e.nextElement();


                    System.out.println("id -> " + id + "\ncost -> " + ec2SumValesDict.get(id).getSummedPriced() +
                            "\nSummed Usage -> " + ec2SumValesDict.get(id).getSummedUsageQuantity());


                    predictedPrice = predictedPrice + GetPredictedCostForBudgetEndDate(ec2SumValesDict.get(id).getSummedPriced(),
                            responseModel.getEndDate());

                }

                Enumeration<String> e2 = ec2SumValesDict.keys();

                double predictedEc2CostPercentage = GetPercentageOfBudget(responseModel, predictedPrice);
                double mockPredictedEc2CostPercentage = 0.07;

                System.out.println("percentage left of budget -> "+responseModel.GetRemainingPercentage());

                if (mockPredictedEc2CostPercentage >= responseModel.GetRemainingPercentage()) {

                    while (e2.hasMoreElements()) {
                        instanceIdsToShutdown.add(e2.nextElement());
                    }

                    awsCLIRequest.StopEC2Instances(instanceIdsToShutdown);

                } else {

                    System.out.println("i");

                    while (e2.hasMoreElements()) {



                        //System.out.println("ec2 instance percentage -> " + GetPercentageOfBudget(responseModel,ec2SumValesDict.get(e2.nextElement()).getSummedPriced()));

                        //TODO: finish implementation
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

        // Based on the last 14 days
        double dayCost = ec2InstanceSum / 14;

        return dayCost * daysLeftOfBudget;

    }



}
