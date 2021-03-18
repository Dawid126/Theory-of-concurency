import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Scheduler extends Thread{
    private Queue<IMethodRequest> activationQueue;

    public Scheduler() {
        activationQueue = new ConcurrentLinkedQueue<IMethodRequest>();
    }

    public void enqueue(IMethodRequest request) {
        activationQueue.add(request);
    }

    public void run() {
        while (true) {
            IMethodRequest methodRequest = activationQueue.poll();
            if (methodRequest != null) {
                if (methodRequest.guard()) {
                    methodRequest.call();
                } else {
                    activationQueue.add(methodRequest);
                }
            }
        }
    }

}
