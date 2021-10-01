package thread.base;

import json.JsonParser;
import json.model.ec2.operation.EC2InstanceOperation;

public abstract class EC2InstanceTask extends ShellScriptTask {

    /**
     * Input arg shall be the ec2 instance id
     * @param scriptName
     * @param inputArgs
     */
    public EC2InstanceTask(String scriptName, String[] inputArgs) {
        super(scriptName, inputArgs);
    }

    /**
     * Run the shell script
     * @return
     */
    protected EC2InstanceOperation RunEC2InstanceShellScript() {
        String jsonResponse = RunShellScript();

       return GetEC2InstanceOperation(jsonResponse);
    }

    /**
     * Parses the Json String and converts it into an EC2InstanceOperation type
     * @param jsonResponse
     * @return
     */

    protected EC2InstanceOperation GetEC2InstanceOperation(String jsonResponse) {

        System.out.println(jsonResponse);

        JsonParser jsonParser = new JsonParser();
        return jsonParser.ParseJsonString(jsonResponse,new EC2InstanceOperation());

    }

    /**
     * Handle the output from the ec2 instance operation
     */
    protected abstract void HandleEC2InstanceOperation(EC2InstanceOperation instanceOperation);
}
