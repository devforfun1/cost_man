package db;

import Enum.db.DBPropertiesKeys;
import util.PropUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import static sys.ProjectPaths.DB_PROPERTIES_FILE_NAME;

public class DBConnection {


    private String connectionString;
    private String user;
    private String password;


    private static volatile DBConnection instance;
    private static Object mutex = new Object();

    private DBConnection() {

        Init();
    }

    public static DBConnection getInstance() {

        DBConnection result = instance;

        if (result == null) {

            synchronized (mutex) {
                result = instance;

                if (result == null) {
                    instance = result = new DBConnection();
                }
            }
        }
        return result;
    }

    private void Init(){

        Properties properties = null;

        try {
          properties =  PropUtil.ReadPropertiesFile(DB_PROPERTIES_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();

            return;
        }


        ProcessBuilder pb = new ProcessBuilder();

       Map<String,String> env =  pb.environment();

        connectionString = env.get(properties.getProperty(DBPropertiesKeys.CONNECTION_STRING.toString()));
        user = env.get(properties.getProperty(DBPropertiesKeys.USER.toString()));
        password = env.get(properties.getProperty(DBPropertiesKeys.PASSWORD.toString()));

    }


    public Connection Connect() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
