package thread;

import thread.base.ShellScriptTask;

public class GetEC2InfoTask extends ShellScriptTask implements Runnable{

    public GetEC2InfoTask(String scriptName) {
        super(scriptName);
    }

    @Override
    public void run() {

     String jsonResponse =   RunShellScript();

     HandleJsonResponse(jsonResponse);
    }

    private void HandleJsonResponse(String jsonResponse){

        System.out.println(jsonResponse);
        //TODO:handle
    }
}
