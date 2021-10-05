package factory.priority;

import priority.CombinedPriority;

public class CombinedFactory extends PriorityFactory {
    @Override
    PriorityType getFactory() {
        //TODO: Implement this
        return new CombinedPriority();
    }
}
