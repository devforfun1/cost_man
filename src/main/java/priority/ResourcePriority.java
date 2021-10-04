package priority;

import Enum.priority.PriorityQueueType;
import factory.priority.PriorityType;

public class ResourcePriority implements PriorityType {


    public ResourcePriority() {
    }

    @Override
    public PriorityQueueType GetQueueType() {
        return PriorityQueueType.RESOURCE_IDS;
    }
}
