package priority;

import java.util.List;
import java.util.PriorityQueue;

public abstract class Priority<T> {

    protected PriorityQueue<T> priorityQueue;


    public Priority() {

        priorityQueue = new PriorityQueue<>();
    }

      PriorityQueue<T> GetPriorityQueue(){

          return priorityQueue;
      }


    abstract void InitPriorityQueue();


}

