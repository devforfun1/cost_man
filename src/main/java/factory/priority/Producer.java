package factory.priority;

import Enum.priority.PriorityQueueType;

public class Producer {

    private static PriorityFactory priorityFactory;


    public static PriorityType GetFactory(PriorityQueueType queueType){



        switch(queueType){
            case RESOURCE_IDS:
                priorityFactory = (PriorityFactory) ResourcePriority().getFactory();
                break;
            case RESOURCE_GROUPS:
                priorityFactory = (PriorityFactory) ResourceGroupPriority().getFactory();
                break;
            case COMBINED:
                priorityFactory = (PriorityFactory) CombinedPriority().getFactory();
        }

        return (PriorityType) priorityFactory;
    }

    private static PriorityFactory ResourcePriority(){


        return new ResourcePriorityFactory();
    }

    private static PriorityFactory ResourceGroupPriority(){

        return new ResourceGroupFactory();
    }

    private static PriorityFactory CombinedPriority(){

        return new CombinedFactory();
    }


}
