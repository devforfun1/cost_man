package thread.ec2;

import datastorage.ResourceStorage;
import json.JsonParser;
import json.model.ec2.ec2data.EC2DataJson;
import json.model.ec2.ec2data.sub.Instance;
import json.model.ec2.ec2data.sub.Reservation;
import thread.base.ShellScriptTask;



/**
 * Gets data from multiply EC2 instances
 */

public class GetEC2InfoTask extends ShellScriptTask implements Runnable {


    public GetEC2InfoTask(String scriptName) {
        super(scriptName);
    }

    @Override
    public void run() {

        String jsonResponse = RunShellScript();

        HandleJsonResponse(jsonResponse);
    }

    private void HandleJsonResponse(String jsonResponse) {

        System.out.println(jsonResponse);

        JsonParser jsonParser = new JsonParser();
        EC2DataJson ec2DataJson = jsonParser.ParseJsonString(jsonResponse, new EC2DataJson());

        ec2DataJson.getReservations()
                .forEach(r -> r.getInstances()
                        .forEach(i -> System.out.println("Instances -> " + i.getInstanceId() + "\nState -> " + i.getState().getName())));


        for (Reservation r : ec2DataJson.getReservations()) {

            for (Instance e : r.getInstances()) {

                if (e.getState().getCode() == 16)
                    ResourceStorage.getInstance().AddEc2StoppedInstance(e.getInstanceId());

                else if (e.getState().getCode() == 80)
                    ResourceStorage.getInstance().AddEc2RunningInstance(e.getInstanceId());

            }
        }

        ResourceStorage.getInstance().Ec2OperationRunning(false);

    }
}
