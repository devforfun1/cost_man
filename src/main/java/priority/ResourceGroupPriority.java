package priority;

import Enum.priority.PriorityQueueType;
import Enum.priority.ResourceGroupPriorities;
import factory.priority.PriorityType;

import java.util.Queue;

public class ResourceGroupPriority extends PriorityBase<ResourceGroupPriorities> implements PriorityType {


    public ResourceGroupPriority() {
    }



    @Override
    public PriorityQueueType GetQueueType() {
        return PriorityQueueType.RESOURCE_GROUPS;
    }


    public void SetQueue(Queue<ResourceGroupPriorities> priorityQueue) {

        this.setPriorityQueue(priorityQueue);
    }


}
