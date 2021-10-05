package factory.priority;

import priority.ResourcePriority;

public class ResourcePriorityFactory extends PriorityFactory {



    @Override
    PriorityType getFactory() {
        //TODO: Implement this
        return new ResourcePriority();
    }
}
