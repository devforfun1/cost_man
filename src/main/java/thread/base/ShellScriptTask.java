package thread.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import static sys.ProjectPaths.RESOURCES_PATH;
import static sys.ProjectPaths.SHELL_SCRIPT_PATH;


public abstract class ShellScriptTask {


    private final String PATH_TO_SCRIPT;
    private final String SH = "sh";
    private String[] inputArgs;

    private ProcessBuilder pb;


    public ShellScriptTask(String scriptName) {

        PATH_TO_SCRIPT = RESOURCES_PATH + SHELL_SCRIPT_PATH + scriptName;
    }

    public ShellScriptTask(String scriptName, String[] inputArgs) {
        this.inputArgs = inputArgs;
        PATH_TO_SCRIPT = RESOURCES_PATH + SHELL_SCRIPT_PATH + scriptName;

    }

    protected String RunShellScript() {

        pb = new ProcessBuilder();

        SetCommands(pb);

        try {

            Process process = pb.start();

            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {

                sb.append(line);

            }

            return sb.toString();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "Error";
    }


    private void SetCommands(ProcessBuilder pb) {

        List<String> cmdList = new LinkedList<>();

        cmdList.add(SH);
        cmdList.add(PATH_TO_SCRIPT);

        if (inputArgs != null)
            cmdList.addAll(Arrays.asList(inputArgs));

        pb.command(cmdList);


    }

}
