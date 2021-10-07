package factory.priority;

import datastorage.db.PriorityService;
import datastorage.db.model.PriorityQueueModel;
import datastorage.db.model.PriorityQueuePlaceModel;

public abstract class PriorityFactory {

    protected PriorityService priorityService = new PriorityService();
    protected PriorityQueueModel queueModel;
    protected PriorityQueuePlaceModel placeModel;


    abstract void InitFactory();



}
