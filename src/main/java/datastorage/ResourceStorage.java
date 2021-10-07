package datastorage;


import annonation.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ResourceStorage {


    private static volatile ResourceStorage instance;
    private static Object mutex = new Object();

    private  volatile Boolean ec2OperationRunning;


    private List<String> ec2RunningInstances;
    private List<String> ec2StoppedInstances;

    private ResourceStorage() {

        ec2RunningInstances = new ArrayList<>();
        ec2StoppedInstances = new ArrayList<>();

        ec2OperationRunning = false;


    }

    public static ResourceStorage getInstance() {

        ResourceStorage result = instance;

        if (result == null) {

            synchronized (mutex) {
                result = instance;

                if (result == null) {
                    instance = result = new ResourceStorage();
                }
            }
        }
        return result;
    }

    public List<String> getEc2RunningInstances() {
        return ec2RunningInstances;
    }

    public void AddEc2RunningInstance(String ec2RunningInstance) {
        ec2RunningInstances.add(ec2RunningInstance);
    }

    public void AddEc2StoppedInstance(String ec2StoppedInstance) {
        ec2StoppedInstances.add(ec2StoppedInstance);
    }

    public List<String> getEc2StoppedInstances() {
        return ec2StoppedInstances;
    }


    public void ClearLists() {

        ec2RunningInstances.clear();
        ec2StoppedInstances.clear();

        ec2RunningInstances = new ArrayList<>();
        ec2StoppedInstances = new ArrayList<>();
    }


    public Boolean IsEc2OperationRunning() {
        return ec2OperationRunning;
    }

    public  void Ec2OperationRunning(Boolean ec2OperationRunning) {
        this.ec2OperationRunning = ec2OperationRunning;
    }

}
