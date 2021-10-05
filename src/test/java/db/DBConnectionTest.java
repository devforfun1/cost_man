package db;

import com.sun.tools.javac.util.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DBConnectionTest {



    @Test
    public void ConnectToDB(){

       Connection conn = DBConnection.getInstance().Connect();

        Assert.checkNonNull(conn);
    }
}
