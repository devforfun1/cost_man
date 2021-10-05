package datastorage.db;

import Enum.priority.PriorityQueueType;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PriorityService {


    public PriorityService() {
    }

    public PriorityQueueType GetPriorityQueueType() throws SQLException {

        PriorityQueueType queueType = null;

        String sql = "SELECT pq.* FROM Prio_Queue pq JOIN Prio_Queue_Selected ps ON pq.id = ps.id;\n";
        PreparedStatement ps = DBConnection.getInstance().Connect().prepareStatement(sql);


        ResultSet rs = ps.executeQuery();

        int type = 0;

        while(rs.next()){

            type =  rs.getInt("type");

        }

        switch (type) {
            case 1:
                queueType = PriorityQueueType.RESOURCE_IDS;
                break;
            case 2:
                queueType = PriorityQueueType.RESOURCE_GROUPS;
                break;
            case 3:
                queueType = PriorityQueueType.COMBINED;

        }

        return queueType;

    }
}
