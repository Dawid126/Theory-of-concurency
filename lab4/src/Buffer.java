import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Buffer {

    private List<Integer> bufferList = new ArrayList<>();
    private final int maxSize;

    final ReentrantLock lock = new ReentrantLock();
    final Condition firstProducer  = lock.newCondition();
    final Condition firstConsumer = lock.newCondition();
    final Condition restProducers = lock.newCondition();
    final Condition restConsumers = lock.newCondition();

    public Buffer(int M) {
        this.maxSize = 2 * M;
    }

    public void put(ArrayList<Integer> items) throws InterruptedException{
        lock.lock();
        try {
            if(lock.hasWaiters(firstProducer)) restProducers.await();

            while(bufferList.size() + items.size() > maxSize)
                firstProducer.await();

            bufferList.addAll(items);
            System.out.println("Adding " + items.size() + " elements");

            restProducers.signal();
            firstConsumer.signal();

        } finally {
            lock.unlock();
        }

    }

    public void get(int size) throws InterruptedException {
        lock.lock();
        try {
            if(lock.hasWaiters(firstConsumer)) restConsumers.await();
            while(bufferList.size() < size)
                firstConsumer.await();

            for(int i=0; i<size; i++) {
                bufferList.remove(bufferList.size() - 1);
            }
            System.out.println("Getting " + size + " elements");

            restConsumers.signal();
            firstProducer.signal();

        } finally {
            lock.unlock();
        }
    }

    public int getMaxSize() {
        return maxSize;
    }
}
