package priority;

import Enum.priority.PriorityQueueType;
import Enum.priority.ResourceGroupPrioritys;
import factory.priority.PriorityType;

public class ResourceGroupPriority extends Priority<ResourceGroupPrioritys> implements PriorityType {

    public ResourceGroupPriority() {
    }

    @Override
    void InitPriorityQueue() {

        // Temp
        priorityQueue.add(ResourceGroupPrioritys.EC2);
    }

    @Override
    ResourceGroupPrioritys GetPrioritizedElement() {
        return priorityQueue.poll();
    }


    @Override
    public PriorityQueueType GetQueueType() {
        return PriorityQueueType.RESOURCE_GROUPS;
    }




}
