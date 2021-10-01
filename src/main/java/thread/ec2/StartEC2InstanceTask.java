package thread.ec2;

import json.JsonParser;
import json.model.ec2.operation.EC2InstanceOperation;
import thread.base.EC2InstanceTask;
import thread.base.ShellScriptTask;

public class StartEC2InstanceTask extends EC2InstanceTask implements Runnable {

    public StartEC2InstanceTask(String scriptName, String inputArgs) {
        super(scriptName, new String[]{inputArgs});
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



