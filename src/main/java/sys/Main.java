package sys;

import Enum.AwsRequest;
import aws.handler.AwsRequestHandler;



public class Main {

    public static void main(String args[]) {


        Init.Start();



        AwsRequestHandler awsRequestHandler = new AwsRequestHandler();

        awsRequestHandler.HandleRequest(AwsRequest.COST_EXPLORER_JSON_DATA);

    }


}
