package factory.priority;

import Enum.priority.PriorityQueueType;

public class Producer {

    private static AbstractFactory priorityFactory;


    public static PriorityType GetFactory(PriorityQueueType queueType){



        switch(queueType){
            case RESOURCE_IDS:
                priorityFactory = (AbstractFactory) ResourcePriority().getFactory();
                break;
            case RESOURCE_GROUPS:
                priorityFactory = (AbstractFactory) ResourceGroupPriority().getFactory();
                break;
            case COMBINED:
                priorityFactory = (AbstractFactory) CombinedPriority().getFactory();
        }

        return (PriorityType) priorityFactory;
    }

    private static AbstractFactory ResourcePriority(){


        return new ResourcePriorityFactory();
    }

    private static AbstractFactory ResourceGroupPriority(){

        return new ResourceGroupFactory();
    }

    private static AbstractFactory CombinedPriority(){

        return new CombinedFactory();
    }


}
