package priority;

import Enum.priority.PriorityQueueType;
import factory.priority.PriorityType;

public class CombinedPriority  implements PriorityType {

    public CombinedPriority() {
    }

    @Override
    public PriorityQueueType GetQueueType() {
        return PriorityQueueType.COMBINED;
    }
}
