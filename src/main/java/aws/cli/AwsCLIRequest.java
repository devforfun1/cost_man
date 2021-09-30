package aws.cli;

import thread.GetEC2InfoTask;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AwsCLIRequest {

    private final String EC2_DATA_SCRIPT_NAME = "ec2List.sh";

    public AwsCLIRequest() {
    }

    public void GetEC2Data() {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new GetEC2InfoTask(EC2_DATA_SCRIPT_NAME));

        executor.shutdown();

    }
}
