package sys;

import Enum.AwsRequest;
import handler.AwsRequestHandler;


public class Main {

    public static void main(String args[]) {


        Init.Start();



        AwsRequestHandler awsRequestHandler = new AwsRequestHandler();

        awsRequestHandler.HandleRequest(AwsRequest.COST_AND_USAGES_WITH_RESOURCES);

    }


}
