package datastorage.db.model;

import Enum.priority.PriorityQueueType;

import java.util.List;

public class PriorityQueueModel {

    private int id;
    private PriorityQueueType type;
    private String name;
    private List<PriorityQueuePlaceModel> queuePlace;




    public PriorityQueueModel(int id, PriorityQueueType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public PriorityQueueType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<PriorityQueuePlaceModel> getPriorityQueuePlaces() {
        return queuePlace;
    }

    public void setQueuePlace(List<PriorityQueuePlaceModel> queuePlace) {
        this.queuePlace = queuePlace;
    }
}
