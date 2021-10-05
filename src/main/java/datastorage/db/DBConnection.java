package datastorage.db;

import Enum.db.DBPropertiesKeys;
import Enum.priority.PriorityQueueType;
import annonation.Singleton;
import util.PropUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import static datastorage.ProjectPaths.DB_PROPERTIES_FILE_NAME;

@Singleton
public class DBConnection {


    private String connectionString;
    private String user;
    private String password;

    private  Connection connection;


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

        Properties properties;

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

    if(connection == null){


        try {
            connection = DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        return connection;
    }




}
