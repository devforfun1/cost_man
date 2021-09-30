package thread;

import json.JsonParser;
import json.model.ec2.EC2DataJson;
import thread.base.ShellScriptTask;

public class GetEC2InfoTask extends ShellScriptTask implements Runnable{

    public GetEC2InfoTask(String scriptName) {
        super(scriptName);
    }

    @Override
    public void run() {

     String jsonResponse =   RunShellScript();

     HandleJsonResponse(jsonResponse);
    }

    private void HandleJsonResponse(String jsonResponse){

        System.out.println(jsonResponse);

        JsonParser jsonParser = new JsonParser();
        EC2DataJson ec2DataJson =  jsonParser.ParseJsonString(jsonResponse,new EC2DataJson());

        ec2DataJson.getReservations()
                .forEach(r -> r.getInstances()
                        .forEach(i -> System.out.println("Instances -> "+i.getInstanceId())));


    }
}
