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


    /**
     * Use when instance ids are unknown
     * @param scriptName
     */
    public GetEC2InfoTask(String scriptName) {
        super(scriptName);
    }

    /**
     * Use when instance ids are known
     * @param scriptName
     * @param ec2InstanceIds
     */
    public GetEC2InfoTask(String scriptName, String[] ec2InstanceIds) {
        super(scriptName, ec2InstanceIds);
    }

    /**
     * Use when instance ids are known
     * Instances ids are separated with " " (space)
     * @param scriptName
     * @param ec2InstanceIdString
     */
    public GetEC2InfoTask(String scriptName, String ec2InstanceIdString) {
        super(scriptName, new String[]{ec2InstanceIdString});
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

                if (e.getState().getCode() == 16){
                    ResourceStorage.getInstance().AddEc2RunningInstance(e.getInstanceId());
                System.out.println("Added instance to RUNNING list with state -> " +e.getState().getName());}

                else if (e.getState().getCode() == 80) {
                    ResourceStorage.getInstance().AddEc2StoppedInstance(e.getInstanceId());
                    System.out.println("Added instance to STOP list with state -> " +e.getState().getName());
                }
            }
        }
        ResourceStorage.getInstance().Ec2OperationRunning(false);



    }
}
