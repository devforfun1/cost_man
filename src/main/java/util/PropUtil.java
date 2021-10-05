package util;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import static datastorage.ProjectPaths.RESOURCES_PATH;

public class PropUtil {


    public static Properties ReadPropertiesFile(String fileName) throws IOException {

        Properties properties = null;

        FileInputStream fileInputStream = null;

        try {


            fileInputStream = new FileInputStream(RESOURCES_PATH + fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException ioException) {

            ioException.printStackTrace();
        } finally {
            if(fileInputStream != null)
            fileInputStream.close();
        }

        return properties;
    }
}
