package handler.response.cost_explorer;


import base.ResponseHandlerBase;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import json.model.cost_and_usages.CostAndUsagesJson;
import json.model.cost_and_usages.sub.Group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
}
