package sys;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.PropUtil;

import java.io.IOException;
import java.util.Properties;



public class InitTest {

    private Properties props;
    private String accountId;
    private String roleArn;


    @Before
    public void Before() {

        props = null;
        accountId = "";
        roleArn = "";

        try {
            props = PropUtil.ReadPropertiesFile("config.properties");
            accountId = props.getProperty("ACCOUNT_ID");
            roleArn = props.getProperty("ROLE_ARN");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ValidateLength_AccountId_Test() {
        final int expected = 12;
        int actual;

        String regex = "[0-9]+";

        actual = accountId.length();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void ContainsOnlyDigits_AccountId_Test() {

        final boolean expected = true;
        boolean actual;

        String regex = "[0-9]+";

        actual = accountId.matches(regex);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void StartsWith_RoleArn_Test() {

        final String expected = "arn:aws:iam::";
        String actual = roleArn.substring(0, 13);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void RoleArnAccountMatchesAccountNr_Test() {

        final boolean expected = true;
        boolean actual;

        String accountNrFromRoleArn = roleArn.substring(13, 25);

        actual = accountNrFromRoleArn.matches(accountId);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void KeyExists_AccountId_Test(){

        final boolean expected = true;
        boolean actual;

       actual = props.containsKey("ACCOUNT_ID");

       Assert.assertEquals(expected,actual);
    }

    @Test
    public void KeyExists_RoleArn_Test(){

        final boolean expected = true;
        boolean actual;

        actual = props.containsKey("ROLE_ARN");

        Assert.assertEquals(expected,actual);

    }

    @Test
    public void KeyExists_BudgetName_Test(){

        final boolean expected = true;
        boolean actual;

        actual = props.containsKey("BUDGET_NAME");

        Assert.assertEquals(expected,actual);

    }
}
