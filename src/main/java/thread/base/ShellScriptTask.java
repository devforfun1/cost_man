package thread.base;

import sys.ProjectPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static sys.ProjectPaths.RESOURCES_PATH;
import static sys.ProjectPaths.SHELL_SCRIPT_PATH;

public abstract class ShellScriptTask {


    private final String PATH_TO_SCRIPT;

    public ShellScriptTask(String scriptName) {


        PATH_TO_SCRIPT = RESOURCES_PATH + SHELL_SCRIPT_PATH + scriptName;
    }

    protected String RunShellScript() {

        Process process;

        ProcessBuilder pb = new ProcessBuilder("sh", PATH_TO_SCRIPT);

        try {

            process = pb.start();

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
}
