package factory.priority;


import Enum.priority.ResourceType;
import datastorage.db.model.PriorityQueuePlaceModel;
import priority.ResourceGroupPriority;
import priority.ResourcePriority;
import priority.model.ResourcePriorityQueueElement;

import java.util.LinkedList;
import java.util.Queue;

public class ResourcePriorityFactory extends PriorityFactory implements Factory<ResourcePriority> {


    @Override
    void InitFactory() {

        queueModel = priorityService.GetSelectedPriorityQueue();
    }


    @Override
    public ResourcePriority Create() {
        ResourcePriority resourcePriority = new ResourcePriority();


        Queue<ResourcePriorityQueueElement> priorityQueue = new LinkedList<>();

        PriorityQueuePlaceModel firstPrio = queueModel.getPriorityQueuePlaces().stream().filter(e -> e.getParentId() == 0).findFirst().get();



        PriorityQueuePlaceModel curr = firstPrio;

        for (int i = 0; i < queueModel.getPriorityQueuePlaces().size(); i++) {


            if (curr.getParentId() != 0)
                priorityQueue.add(new ResourcePriorityQueueElement(curr.getElementData(),ConvertElementType(curr.getElementType())));

            PriorityQueuePlaceModel finalCurr = curr;

            if(curr.getChildId() != 0)
                curr = queueModel.getPriorityQueuePlaces().stream().filter(e -> e.getElementId() == finalCurr.getChildId()).findFirst().get();
        }


        resourcePriority.SetQueue(priorityQueue);
        return resourcePriority;
    }

    private ResourceType ConvertElementType(int id) {

        if (id == 1)
            return ResourceType.EC2;
        else if (id == 2)
            return ResourceType.VPC;
        else if (id == 3)
            return ResourceType.EKS;

        else
            return null;
    }
}
