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
import java.util.Queue;


public class Main {

    public static void main(String args[]) {

        Init.Start();


        AwsRequestHandler awsRequestHandler = new AwsRequestHandler();

        awsRequestHandler.HandleRequest(AwsRequest.MONTHLY_BUDGET);

    }


}
