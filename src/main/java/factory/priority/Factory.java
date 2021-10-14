package factory.priority;

import priority.PriorityBase;

public interface Factory<T extends PriorityBase> {

    T Create();

}
