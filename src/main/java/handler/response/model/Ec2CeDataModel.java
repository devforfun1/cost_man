package handler.response.model;

/**
 * Data holder for the Value in the returned Dictionary from method: GetEc2UsagesValueDict-
 * in class: CostExplorerRequest
 */
public class Ec2CeDataModel {

    private double summedUsageQuantity;
    private double summedPriced;

    public Ec2CeDataModel(double summedUsageQuantity, double summedPriced) {
        this.summedUsageQuantity = summedUsageQuantity;
        this.summedPriced = summedPriced;
    }

    public double getSummedUsageQuantity() {
        return summedUsageQuantity;
    }

    public double getSummedPriced() {
        return summedPriced;
    }
}
