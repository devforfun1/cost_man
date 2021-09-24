package util;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropUtil {

    private static final String FILE_NAME = "prop.properties";
    private static final String PROJECT_PATH = "src/main/resources/";

    public static Properties ReadPropertiesFile() throws IOException {

        Properties properties = null;

        FileInputStream fileInputStream = null;

        try {


            fileInputStream = new FileInputStream(PROJECT_PATH + FILE_NAME);
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
