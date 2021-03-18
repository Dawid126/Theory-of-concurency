import java.util.ArrayList;
import java.util.List;

public class Buffer {

    private List<Item> bufferList = new ArrayList<>();
    private final int maxSize;
    private int numOfItems = 0;
    private int lastFreeSlot = 0;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        for(int i=0; i<maxSize; i++) {
            bufferList.add(null);
        }
    }

    public synchronized void put(Item item) {
        while(numOfItems >= this.maxSize) {
            try {
                System.out.println("Thread " + Thread.currentThread().getId() + " waiting to put items");
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=lastFreeSlot ;; i = (i+1) % maxSize) {
            if(bufferList.get(i) == null) {

                bufferList.set(i, item);
                lastFreeSlot = i;

                numOfItems++;

                System.out.println("Thread " + Thread.currentThread().getId() + " adds item of value " + item.getValue() + " at position " + i);
                break;
            }
        }
        notify();
    }

    public synchronized Item get(Consumer consumer){
        while (numOfItems <= 0) {
            try {
                System.out.println("Thread " + Thread.currentThread().getId() + ".Number of items = 0 in consumer");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int lastPosition = consumer.getLastPosition();
        Item item = bufferList.get(lastPosition);

        try {
            while (item == null || item.getStageOfProduction() != 5) {
                System.out.println("Thread " + Thread.currentThread().getId() + ".Item not fully processed or not present");
                wait();
            }

            bufferList.set(lastPosition, null);
            System.out.println("Thread " + Thread.currentThread().getId() + " gets item with processed value of " + item.getValue() +
                    "at position " + lastPosition);
            consumer.setLastPosition((lastPosition + 1) % maxSize);
            numOfItems--;

            notify();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public synchronized void processItem(Processor processor) throws Exception {
        while (numOfItems <= 0) {
            try {
                System.out.println("Thread " + Thread.currentThread().getId() + ".Number of items = 0 in buffer: proccesor");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int lastPosition = processor.getLastPosition();
        Item item = bufferList.get(lastPosition);

        while (item == null || item.getStageOfProduction() != processor.getStageOfProductionID()) {
            wait();
        }
        System.out.println("Thread " + Thread.currentThread().getId() + "procceses item " + item.getValue() + " at position " + lastPosition + ". Stage " +
                "of production " + processor.getStageOfProductionID());

        item.process();

        System.out.println("Thread " + Thread.currentThread().getId() + "proccesed item at position" + lastPosition + ".New value" +
                " " + item.getValue());
        processor.setLastPosition((lastPosition + 1) % maxSize);
        notify();
    }
}
