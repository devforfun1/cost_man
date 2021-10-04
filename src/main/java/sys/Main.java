package sys;

import Enum.request.AwsRequest;
import handler.AwsRequestHandler;


public class Main {

    public static void main(String args[]) {


        Init.Start();



        AwsRequestHandler awsRequestHandler = new AwsRequestHandler();

        awsRequestHandler.HandleRequest(AwsRequest.EC2_DATA);

    }


}
