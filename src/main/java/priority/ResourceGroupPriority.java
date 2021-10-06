package priority;

import Enum.priority.PriorityQueueType;
import Enum.priority.ResourceGroupPriorities;
import datastorage.db.model.PriorityQueueModel;
import datastorage.db.model.PriorityQueuePlaceModel;
import factory.priority.PriorityType;

import java.util.List;
import java.util.PriorityQueue;

public class ResourceGroupPriority extends PriorityBase<ResourceGroupPriorities> implements PriorityType {


    public ResourceGroupPriority() {
    }



    @Override
    public PriorityQueueType GetQueueType() {
        return PriorityQueueType.RESOURCE_GROUPS;
    }


    public void SetQueue(PriorityQueue<ResourceGroupPriorities> priorityQueue) {

        this.setPriorityQueue(priorityQueue);
    }


}
