package sys;

import singleton.DataStorage;
import util.PropUtil;
import Enum.PropertiesKey;
import java.io.IOException;
import java.util.Properties;

public class Init {

    public static void Start() {

        try {
            Properties props = PropUtil.ReadPropertiesFile();
            DataStorage.getInstance().setAwsAccountNr(props.getProperty(PropertiesKey.ACCOUNT_ID.toString()));
            DataStorage.getInstance().setRoleArn(props.getProperty(PropertiesKey.ROLE_ARN.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
