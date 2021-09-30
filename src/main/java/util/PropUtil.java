package util;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import static sys.ProjectPaths.PROPERTIES_FILE_NAME;
import static sys.ProjectPaths.RESOURCES_PATH;

public class PropUtil {


    public static Properties ReadPropertiesFile() throws IOException {

        Properties properties = null;

        FileInputStream fileInputStream = null;

        try {


            fileInputStream = new FileInputStream(RESOURCES_PATH + PROPERTIES_FILE_NAME);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException ioException) {

            ioException.printStackTrace();
        } finally {
            fileInputStream.close();
        }

        return properties;
    }
}
