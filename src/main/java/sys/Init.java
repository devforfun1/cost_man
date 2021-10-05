package sys;

import db.DBConnection;
import singleton.DataStorage;
import util.PropUtil;
import Enum.init.ConfigPropertiesKeys;

import java.io.IOException;
import java.util.Properties;

import static sys.ProjectPaths.CONFIG_PROPERTIES_FILE_NAME;

public class Init {

    public static void Start() {

        try {
            Properties props = PropUtil.ReadPropertiesFile(CONFIG_PROPERTIES_FILE_NAME);
            DataStorage.getInstance().setAwsAccountNr(props.getProperty(ConfigPropertiesKeys.ACCOUNT_ID.toString()));
            DataStorage.getInstance().setRoleArn(props.getProperty(ConfigPropertiesKeys.ROLE_ARN.toString()));
            DataStorage.getInstance().setBudgetName(props.getProperty(ConfigPropertiesKeys.BUDGET_NAME.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        InitDB();

    }


    private static void InitDB() {

        DBConnection.getInstance();

    }
}
