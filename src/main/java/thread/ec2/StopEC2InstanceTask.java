package thread.ec2;

import json.model.ec2.operation.EC2InstanceOperation;
import thread.base.EC2InstanceTask;


public class StopEC2InstanceTask extends EC2InstanceTask implements Runnable {

    public StopEC2InstanceTask(String scriptName, String instanceId) {
        super(scriptName, new String[]{instanceId});

    }

    @Override
    protected void HandleEC2InstanceOperation(EC2InstanceOperation instanceOperation) {

        System.out.println(instanceOperation.toString());

        //TODO: Implement
    }


    @Override
    public void run() {

        HandleEC2InstanceOperation(RunEC2InstanceShellScript());
    }


}
