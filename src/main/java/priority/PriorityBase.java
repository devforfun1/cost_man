package priority;

import java.util.Queue;

public class PriorityBase<T> {


    /**
     * The Queue should be based on a priority policy
     */
    private Queue<T> priorityQueue;

    public Queue<T> getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(Queue<T> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }
}
