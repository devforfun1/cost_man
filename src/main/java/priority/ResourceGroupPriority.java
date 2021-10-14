package priority;

import Enum.priority.PriorityQueueType;
import Enum.priority.ResourceType;
import factory.priority.PriorityType;

import java.util.Queue;

public class ResourceGroupPriority extends PriorityBase<ResourceType> implements PriorityType {


    public ResourceGroupPriority() {
    }



    @Override
    public PriorityQueueType GetQueueType() {
        return PriorityQueueType.RESOURCE_GROUPS;
    }


    public void SetQueue(Queue<ResourceType> priorityQueue) {

        this.setPriorityQueue(priorityQueue);
    }


}
