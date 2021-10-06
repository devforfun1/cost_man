package sys;

import Enum.request.AwsRequest;
import datastorage.db.PriorityService;
import factory.priority.PriorityFactory;
import factory.priority.Producer;
import factory.priority.ResourceGroupFactory;
import handler.request.AwsRequestHandler;
import priority.ResourceGroupPriority;

import java.sql.SQLException;
import java.util.PriorityQueue;


public class Main {

    public static void main(String args[]) {

        Init.Start();

        ResourceGroupFactory factory = (ResourceGroupFactory) Producer.GetFactory();

        ResourceGroupPriority rg = factory.Create();

        PriorityQueue pq = rg.getPriorityQueue();


        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());


        AwsRequestHandler awsRequestHandler = new AwsRequestHandler();

        awsRequestHandler.HandleRequest(AwsRequest.EC2_DATA);

    }


}
