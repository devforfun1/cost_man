package factory.priority;

public interface Factory<T> {

    T Create();
    void InitPriorityQueue();
}
