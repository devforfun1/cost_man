package sys;

import Enum.request.AwsRequest;
import datastorage.db.PriorityService;
import handler.request.AwsRequestHandler;

import java.sql.SQLException;


public class Main {

    public static void main(String args[]) throws SQLException {


        Init.Start();

        PriorityService priorityService = new PriorityService();
        System.out.println(priorityService.GetPriorityQueueType().name());

        AwsRequestHandler awsRequestHandler = new AwsRequestHandler();

        awsRequestHandler.HandleRequest(AwsRequest.EC2_DATA);

    }


}
