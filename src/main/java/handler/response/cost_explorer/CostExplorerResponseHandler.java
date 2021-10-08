package handler.response.cost_explorer;


import handler.response.base.ResponseHandlerBase;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import handler.response.model.Ec2CeDataModel;
import json.model.cost_and_usages.CostAndUsagesJson;
import json.model.cost_and_usages.sub.Group;

import java.util.*;
import java.util.stream.Collectors;


public class CostExplorerResponseHandler extends ResponseHandlerBase {

    public CostExplorerResponseHandler() {
    }

    public void CostAndUsagesWithGroupByResult(GetCostAndUsageResult result) {

        result.getResultsByTime().forEach(resultsByTime -> {
            System.out.println(resultsByTime.toString());
        });
    }

    public void CostAndUsagesJsonResult(CostAndUsagesJson cauResult) {

        List<Group> groupList = new ArrayList<>();

        cauResult.getResultsByTime()
                .forEach(r -> r.getGroups().stream().filter(g -> !g.getMetrics().getUnblendedCost().getAmount().equals("0"))
                        .forEach(g -> groupList.add(g)));


        Collections.sort(groupList, (o1, o2) -> o2.getMetrics().getUnblendedCost().GetAmountDoubleValue().compareTo(o1.getMetrics().getUnblendedCost().GetAmountDoubleValue()));

        groupList.forEach(g -> System.out.println(g.getKeys().toString() + g.getMetrics().getUnblendedCost().getAmount().toString()));


        Double totalValue = groupList.stream().collect(Collectors.summarizingDouble(g -> g.getMetrics().getUnblendedCost().GetAmountDoubleValue())).getSum();

        System.out.println("Total amount -> " + totalValue + "$");
    }

    /**
     * Sums the usage quantity amount from the cost and usages api with resources -
     * for the given instance id, given a time interval defined in the cauResult
     * <p>
     * The returned Dictionary contains a String with the EC2 instance id and the summed usage quantity
     *
     * @param cauResult
     * @param ec2InstanceIds
     * @return
     */
    public Dictionary<String, Ec2CeDataModel> Ec2UsagesValueDict(CostAndUsagesJson cauResult, List<String> ec2InstanceIds) {

        Dictionary<String, Ec2CeDataModel> ec2UsageDict = new Hashtable<>();

        for (String instanceId : ec2InstanceIds) {

            List<Group> ec2Entries = new ArrayList<>();

            cauResult.getResultsByTime().
                    forEach(r -> r.getGroups().stream().
                            filter(g -> g.getKeys().contains(instanceId)).
                            forEach(g -> ec2Entries.add(g)));


            double usageQuantity = ec2Entries.stream().
                    collect(Collectors.
                            summarizingDouble(ec2Usage -> ec2Usage.getMetrics().
                                    getUsageQuantity().
                                    GetAmountDoubleValue())).
                    getSum();

            double unblendedCost = ec2Entries.stream().collect(Collectors.summarizingDouble(g -> g.getMetrics().
                    getUnblendedCost().
                    GetAmountDoubleValue())).getSum();

            ec2UsageDict.put(instanceId, new Ec2CeDataModel(usageQuantity,unblendedCost));

        }

        return ec2UsageDict;

    }

    public void CostAndUsagesWithResourcesJsonResult(CostAndUsagesJson cauResult) {


        cauResult.getResultsByTime().forEach(r -> r.getGroups().forEach(g -> System.out.println(g.getKeys() + ", Cost(Unblended): "
                + g.getMetrics().getUnblendedCost().getAmount() + ", Usage Quantity: " + g.getMetrics().getUsageQuantity().getAmount()
                + g.getMetrics().getAdditionalProperties())));

    }
}
