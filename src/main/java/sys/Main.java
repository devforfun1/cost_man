package sys;

import Enum.request.AwsRequest;
import datastorage.db.PriorityService;
import handler.request.AwsRequestHandler;

import java.sql.SQLException;


public class Main {

    public static void main(String args[]) {


        Init.Start();


        AwsRequestHandler awsRequestHandler = new AwsRequestHandler();

        awsRequestHandler.HandleRequest(AwsRequest.EC2_DATA);

    }


}
