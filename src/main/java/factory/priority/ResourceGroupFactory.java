package factory.priority;

import priority.ResourceGroupPriority;


public class ResourceGroupFactory extends AbstractFactory {
    @Override
    PriorityType getFactory() {
        //TODO: Implement this
        return new ResourceGroupPriority();
    }
}
