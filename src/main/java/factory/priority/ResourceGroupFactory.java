package factory.priority;


import Enum.priority.ResourceType;
import datastorage.db.model.PriorityQueuePlaceModel;

import priority.ResourceGroupPriority;


import java.util.LinkedList;

import java.util.Queue;



public class ResourceGroupFactory extends PriorityFactory implements Factory<ResourceGroupPriority> {


    @Override
    void InitFactory() {
        queueModel = priorityService.GetSelectedPriorityQueue();
        queueModel.setQueuePlace(priorityService.GetPriorityQueuePlaces(queueModel.getId()));

    }


    /**
     * Creates a FIFO Queue with priority parameters from the repository
     *
     * @return
     */
    @Override
    public ResourceGroupPriority Create() {

        ResourceGroupPriority resourceGroupPriority = new ResourceGroupPriority();


        Queue<ResourceType> priorityQueue = new LinkedList<>();

        PriorityQueuePlaceModel firstPrio = queueModel.getPriorityQueuePlaces().stream().filter(e -> e.getParentId() == 0).findFirst().get();

        priorityQueue.add(ConvertElementData(firstPrio.getElementId()));

        PriorityQueuePlaceModel curr = firstPrio;

        for (int i = 0; i < queueModel.getPriorityQueuePlaces().size(); i++) {


            if (curr.getParentId() != 0)
                priorityQueue.add(ConvertElementData(curr.getElementId()));

            PriorityQueuePlaceModel finalCurr = curr;

            if(curr.getChildId() != 0)
            curr = queueModel.getPriorityQueuePlaces().stream().filter(e -> e.getElementId() == finalCurr.getChildId()).findFirst().get();
        }

        resourceGroupPriority.SetQueue(priorityQueue);
        return resourceGroupPriority;
    }


    /**
     * Temporary solution
     *
     * @param id
     * @return
     */
    private ResourceType ConvertElementData(int id) {

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
