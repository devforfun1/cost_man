package costman;

import Enum.budget.BudgetStatus;
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

    public Ec2Resource() {
    }

    public List<String> CalculateEc2InstancesToShutdown (Dictionary<String, Ec2CeDataModel> ec2SumValesDict, BudgetStatus status,
                                                         BudgetResponseModel responseModel) throws BudgetNotSupportedException {

        List<String> instanceIdsToShutdown = null;

        if (status == BudgetStatus.CLOSE_TO_LIMIT || status == BudgetStatus.LEVEL2) {

            instanceIdsToShutdown = new ArrayList<>();

            Enumeration<String> e = ec2SumValesDict.keys();

            String id;
            double ec2InstancePortionOfBudget;

            if(status == BudgetStatus.CLOSE_TO_LIMIT){

            while (e.hasMoreElements()) {

                id = e.nextElement();

                ec2InstancePortionOfBudget = GetPercentageOfBudget(responseModel,ec2SumValesDict.get(e).getSummedPriced());




                System.out.println("id -> " + id + "\ncost -> " + ec2SumValesDict.get(id).getSummedPriced() +
                        "\nSummed Usage -> " + ec2SumValesDict.get(id).getSummedUsageQuantity());


                //TODO: Finish implementation
            }

            }
        }
        else{
            throw new BudgetNotSupportedException(status.name());
        }

        return instanceIdsToShutdown;
    }

private double GetPercentageOfBudget(BudgetResponseModel responseModel,double sum){

        return sum/responseModel.getLimit().doubleValue();


}

    /**
     * Returns the predicted cost of an EC2 instance sum
     * The EC2 instances shall hold data from an interval of 14 days
     * @param ec2InstanceSum
     * @param endDate
     * @return
     */
    private double GetPredictedCostForBudgetEndDate(double ec2InstanceSum, LocalDate endDate){

       long daysLeftOfBudget = ChronoUnit.DAYS.between(LocalDate.now(),endDate);

       // Based on the last 14 days
       double dayCost = ec2InstanceSum / 14;

       return dayCost * daysLeftOfBudget;

    }



}
