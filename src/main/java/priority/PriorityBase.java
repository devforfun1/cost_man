package priority;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityBase<T> {



    private Queue<T> priorityQueue;

    public Queue<T> getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(Queue<T> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }
}
