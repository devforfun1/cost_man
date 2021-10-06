package priority;

import java.util.PriorityQueue;

public class PriorityBase<T> {



    private PriorityQueue<T> priorityQueue;

    public PriorityQueue<T> getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(PriorityQueue<T> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }
}
