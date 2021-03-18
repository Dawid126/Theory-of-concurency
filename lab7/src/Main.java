import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int numOfConsumers = 7;
        int numoOfProducers = 4;

        Scheduler scheduler = new Scheduler();
        scheduler.start();
        Servant servant = new Servant(10);
        Proxy proxy = new Proxy(scheduler, servant);

        ArrayList<Producer> producers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();

        for (int i = 0; i < numoOfProducers; i++) {
            producers.add(new Producer(i, proxy));
        }

        for (int i = 0; i < numOfConsumers; i++) {
            consumers.add(new Consumer(i, proxy));
        }

        for (int i = 0; i < numoOfProducers; i++) {
            producers.get(i).start();
        }

        for (int i = 0; i < numOfConsumers; i++) {
            consumers.get(i).start();
        }

        try {
            for (int i = 0; i < numoOfProducers; i++) {
                producers.get(i).join();
            }

            for (int i = 0; i < numOfConsumers; i++) {
                consumers.get(i).join();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
