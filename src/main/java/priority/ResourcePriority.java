package priority;

import Enum.priority.PriorityQueueType;
import factory.priority.PriorityType;

import java.util.PriorityQueue;

public class ResourcePriority implements PriorityType {


    public ResourcePriority() {
    }

    @Override
    public PriorityQueueType GetQueueType() {
        return PriorityQueueType.RESOURCE_IDS;
    }


}
