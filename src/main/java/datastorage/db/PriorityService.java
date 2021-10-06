package datastorage.db;

import Enum.priority.PriorityQueueType;
import datastorage.db.model.PriorityQueueModel;
import datastorage.db.model.PriorityQueuePlaceModel;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PriorityService {


    public PriorityService() {
    }

    public PriorityQueueModel GetSelectedPriorityQueue() {

        PriorityQueueModel priorityQueueModel = null;

        final String sql = "SELECT pq.* FROM Prio_Queue pq JOIN Prio_Queue_Selected ps ON pq.id = ps.id;\n";
        PreparedStatement ps = null;

        int id = 0;
        int type = 0;
        String name = null;
        try {
            ps = DBConnection.getInstance().Connect().prepareStatement(sql);


            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                id = rs.getInt("id");
                type = rs.getInt("type");
                name = rs.getString("name");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PriorityQueueType queueType = null;

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

        return new PriorityQueueModel(id, queueType, name);


    }


   public List<PriorityQueuePlaceModel> GetPriorityQueuePlaces(Integer queueId) {

        final String sql = "SELECT qp.* FROM Prio_Queue pq JOIN Prio_Queue_Place qp ON pq.id = qp.queue_id WHERE pq.id=?;";
        PreparedStatement ps = null;



        List<PriorityQueuePlaceModel> queuePlaces = new LinkedList<>();

        int elementId = 0;

        try {
            ps = DBConnection.getInstance().Connect().prepareStatement(sql);
            ps.setInt(1,queueId);

            ResultSet rs = ps.executeQuery();

            PriorityQueuePlaceModel tmp;


            while (rs.next()) {

                elementId = rs.getInt("element_id");

                tmp = new PriorityQueuePlaceModel(elementId);

                if (rs.getInt("child_element_id") != 0)
                    tmp.setChildId(rs.getInt("child_element_id"));
                if (rs.getInt("parent_element_id") != 0)
                    tmp.setParentId(rs.getInt("parent_element_id"));


                queuePlaces.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return queuePlaces;


    }
}
