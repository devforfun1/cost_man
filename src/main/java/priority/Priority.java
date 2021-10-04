package priority;

import java.util.PriorityQueue;

public abstract class Priority<T> {

    protected PriorityQueue<T> priorityQueue;


    public Priority() {

        priorityQueue = new PriorityQueue<>();
    }

    abstract void InitPriorityQueue();

    abstract T GetPrioritizedElement();



}

