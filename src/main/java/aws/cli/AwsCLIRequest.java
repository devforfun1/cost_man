package aws.cli;

import thread.ec2.GetEC2InfoTask;
import thread.ec2.StartEC2InstanceTask;
import thread.ec2.StopEC2InstanceTask;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AwsCLIRequest {

    private final String EC2_DATA_SCRIPT_NAME = "ec2List.sh";
    private final String EC2_STOP_INSTANCE_SCRIPT_NAME = "ec2StopInstance.sh";
    private final String EC2_Start_INSTANCE_SCRIPT_NAME = "ec2StartInstance.sh";

    public AwsCLIRequest() {
    }

    public void GetEC2Data() {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new GetEC2InfoTask(EC2_DATA_SCRIPT_NAME));

        executor.shutdown();

    }

    public void StopEC2Instance(String instanceId) {

        ExecutorService executor = Executors.newSingleThreadExecutor();


        executor.execute(new StopEC2InstanceTask(EC2_STOP_INSTANCE_SCRIPT_NAME,
                instanceId));


        executor.shutdown();

    }

    public void StartEC2Instance(String instanceId) {

        ExecutorService executor = Executors.newSingleThreadExecutor();


        executor.execute(new StartEC2InstanceTask(EC2_Start_INSTANCE_SCRIPT_NAME,
                instanceId));


        executor.shutdown();


    }


}
