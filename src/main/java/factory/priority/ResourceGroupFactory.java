package factory.priority;


import Enum.priority.ResourceGroupPriorities;
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



    @Override
    public ResourceGroupPriority Create() {

        ResourceGroupPriority resourceGroupPriority = new ResourceGroupPriority();


        Queue<ResourceGroupPriorities> priorityQueue = new LinkedList<>();

        PriorityQueuePlaceModel firstPrio = queueModel.getPriorityQueuePlaces().stream().filter(e -> e.getParentId() == 0).findFirst().get();

        priorityQueue.add(Convert(firstPrio.getElementId()));

        PriorityQueuePlaceModel curr = firstPrio;

        for (int i = 0; i < queueModel.getPriorityQueuePlaces().size(); i++) {


            if (curr.getParentId() != 0)
                priorityQueue.add(Convert(curr.getElementId()));

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
    private ResourceGroupPriorities Convert(int id) {

        if (id == 1)
            return ResourceGroupPriorities.EC2;
        else if (id == 2)
            return ResourceGroupPriorities.VPC;
        else if (id == 3)
            return ResourceGroupPriorities.EKS;

        else
            return null;
    }

    @Override
    public void InitPriorityQueue() {


    }
}
