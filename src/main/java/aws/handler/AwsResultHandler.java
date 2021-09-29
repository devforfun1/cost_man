package aws.handler;

import com.amazonaws.services.costexplorer.model.*;
import json.model.cost_and_usages.CostAndUsagesJson;

public class AwsResultHandler {

    public AwsResultHandler() {
    }


   public void CostAndUsagesWithGroupByResult(GetCostAndUsageResult result) {

           result.getResultsByTime().forEach(resultsByTime -> {
           System.out.println(resultsByTime.toString());
       });
   }

   public void CostAndUsagesJsonResult(CostAndUsagesJson cauResult){

       cauResult.getResultsByTime()
               .forEach(r -> r.getGroups()
                       .forEach(g -> System.out.println(g.getKeys().toString() +g.getMetrics().getUnblendedCost().getAmount().toString())));


   }
}
