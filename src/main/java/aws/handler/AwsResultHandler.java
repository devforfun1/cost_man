package aws.handler;

import com.amazonaws.services.costexplorer.model.*;

import java.util.List;

public class AwsResultHandler {

    public AwsResultHandler() {
    }


   public void CostAndUsagesWithGroupByResult(GetCostAndUsageResult result){




      List<ResultByTime> resultByTimeList = result.getResultsByTime();



      for(ResultByTime res: resultByTimeList){




      }



       result.getResultsByTime().forEach(resultsByTime -> {
           System.out.println(resultsByTime.toString());
       });




   }
}
