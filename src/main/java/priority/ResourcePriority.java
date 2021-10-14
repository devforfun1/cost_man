package priority;

import Enum.priority.PriorityQueueType;
import Enum.priority.ResourceType;
import factory.priority.PriorityType;
import priority.model.ResourcePriorityQueueElement;

import java.util.PriorityQueue;
import java.util.Queue;

public class ResourcePriority extends PriorityBase<ResourcePriorityQueueElement> implements PriorityType {


    public ResourcePriority() {
    }

    @Override
    public PriorityQueueType GetQueueType() {
        return PriorityQueueType.RESOURCE_IDS;
    }

    public void SetQueue(Queue<ResourcePriorityQueueElement> priorityQueue) {

        this.setPriorityQueue(priorityQueue);
    }
}
