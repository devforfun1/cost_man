package aws.cli;

import datastorage.ResourceStorage;
import thread.ec2.GetEC2InfoTask;
import thread.ec2.StartEC2InstanceTask;
import thread.ec2.StopEC2InstanceTask;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class AwsCLIRequest {

    private final String EC2_GET_INSTANCE_INFO_SCRIPT_NAME = "ec2GetInstanceInfo.sh";
    private final String EC2_GET_INSTANCE_INFO_BY_ID_SCRIPT_NAME = "ec2GetInstanceInfoById.sh";
    private final String EC2_STOP_INSTANCE_SCRIPT_NAME = "ec2StopInstance.sh";
    private final String EC2_Start_INSTANCE_SCRIPT_NAME = "ec2StartInstance.sh";


    public AwsCLIRequest() {
    }

    public void GetEC2InstanceInfo() {

        ExecutorService executor = Executors.newSingleThreadExecutor();


        ResourceStorage.getInstance().Ec2OperationRunning(true);




        executor.execute(new GetEC2InfoTask(EC2_GET_INSTANCE_INFO_SCRIPT_NAME));

        executor.shutdown();

    }

    public void  GetEC2InstancesInfoByIds(List<String> ec2InstanceIDs) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        StringBuilder sb = new StringBuilder();

        ec2InstanceIDs.forEach(id -> sb.append(id + " "));

        executor.execute(new GetEC2InfoTask(EC2_GET_INSTANCE_INFO_BY_ID_SCRIPT_NAME,sb.toString().trim()));

        executor.shutdown();

    }


        public void StopEC2Instance(String instanceId) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new StopEC2InstanceTask(EC2_STOP_INSTANCE_SCRIPT_NAME,
                instanceId));

        executor.shutdown();

    }

    public void StopEC2Instances(List<String> instanceIds) {

        final int threadPoolSize = instanceIds.size();

        if (threadPoolSize > 0) {


            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);


            instanceIds.forEach(id -> executor.execute(new StopEC2InstanceTask(EC2_STOP_INSTANCE_SCRIPT_NAME, id)));


            executor.shutdown();
        }
    }

    public void StartEC2Instance(String instanceId) {

        ExecutorService executor = Executors.newSingleThreadExecutor();


        executor.execute(new StartEC2InstanceTask(EC2_Start_INSTANCE_SCRIPT_NAME,
                instanceId));

        executor.shutdown();

    }

    public void StartEC2Instances(List<String> instanceIds) {

        final int threadPoolSize = instanceIds.size();

        if (threadPoolSize > 0) {

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);

            instanceIds.forEach(id -> executor.execute(new StartEC2InstanceTask(EC2_Start_INSTANCE_SCRIPT_NAME, id)));

            executor.shutdown();
        }
    }
}